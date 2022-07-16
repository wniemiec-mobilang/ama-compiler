package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wniemiec.data.java.Encryptor;
import wniemiec.data.java.Encryptors;
import wniemiec.mobilex.ama.models.InlineEventTag;
import wniemiec.mobilex.ama.models.Range;


class EventBehaviorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Encryptor encryptor;
    private List<String> parsedCode;
    private List<String> generatedIds;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public EventBehaviorParser() {
        parsedCode = new ArrayList<>();
        generatedIds = new ArrayList<>();
        encryptor = Encryptors.md5();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(List<String> code) {
        if (isEmpty(code)) {
            return;
        }

        parsedCode = new ArrayList<>();

        for (String line : code) {
            parsedCode.add(parseLine(line));
        }
    }

    private boolean isEmpty(List<String> code) {
        return  (code == null) 
                || code.isEmpty();
    }

    private String parseLine(String line) {
        String parsedLine = line;
        Pattern pattern = Pattern.compile("<[^<>]+>");
        Matcher matcher = pattern.matcher(line);
        Queue<Range<Integer>> tagsToParse = new LinkedList<>();
        StringBuilder codesToAdd = new StringBuilder();

        while (matcher.find()) {
            int indexOfTagBegins = matcher.start();
            int indexOfTagEnds = matcher.end() - 1;
            tagsToParse.add(new Range<>(indexOfTagBegins, indexOfTagEnds));
        }
        
        int offset = 0;
        while (!tagsToParse.isEmpty()) {
            Range<Integer> currentTag = tagsToParse.poll();
            InlineEventTag parsedTag = parseTag(line.substring(currentTag.getBegin(), currentTag.getEnd() + 1));

            if (parsedTag.hasEvent()) {
                int oldLength = currentTag.getEnd() - currentTag.getBegin() + 1;
                int newLength = parsedTag.getCode().length();

                parsedLine = parsedLine.substring(0, currentTag.getBegin() + offset) 
                    + parsedTag.getCode() 
                    + parsedLine.substring(offset + currentTag.getEnd()+1);
                offset += newLength - oldLength;
                
                String attributeAssignmentCode = ";document.getElementById(\"" + parsedTag.getId() + "\")." + parsedTag.getEventName() + " = () => " + parsedTag.getEventValue();
                
                attributeAssignmentCode = attributeAssignmentCode.replace("this", "document.getElementById(\"" + parsedTag.getId() + "\")");
                codesToAdd.append(attributeAssignmentCode);
            }
            //codesToAdd.append(";document.getElementById(\"" + buttonId + "\").onclick = () => " + buttonOnClickValue);    
        }

        parsedLine += codesToAdd.toString();
        parsedLine = parsedLine.replace(";;", ";");

        /*if (hasOnClick(line)) {
            parsedLine = parseOnClick(line);
        }*/

        return parsedLine;
    }

    private InlineEventTag parseTag(String tag) {
        InlineEventTag parsedTag = new InlineEventTag(tag);

        if (hasOnClick(tag)) {
            parseOnClick(parsedTag);
        }

        return parsedTag;
    }

    private boolean hasOnClick(String line) {
        return line.toLowerCase().matches(".*([^A-z]+|)onclick=.*");
    }

    /*
        EX: glossaryContent.innerHTML+=`<button class="item" onclick="openDescription(this);">`
        
        ao achar onclick no behavior
            se possui id
                button_id = id
            senao
                button_id = gera_id_randomico
                button.id = button_id
            button_onclick = button.onclick
            se button_onclick possui 'this'
                substituir 'this' por document.getElementById(button_id)
            addLinha("document.getElementById(button_id).onclick = () => button_onclick")
    */
    private void parseOnClick(InlineEventTag tag) {
        tag.setEventName("onclick");
        tag.setEventValue(extractOnClickValueFrom(tag));
        removeOnClickFrom(tag);

        String id;

        if (hasId(tag)) {
            id = extractIdFrom(tag);
        }
        else {
            id = generateUniqueIdentifier();
            putId(id, tag);
            generatedIds.add(id);
        }

        if (hasThis(tag)) {
            tag.setCode(tag.getCode().replace("this", "document.getElementById(\"" + id + "\")"));
        }

        tag.setId(id);

        /*
        if (!isOnClickInsideATag(line)) {
            return line;
        }

        String parsedLine = line;
        String buttonId;

        if (hasId(line)) {
            buttonId = extractIdFrom(line);
        }
        else {
            buttonId = generateUniqueIdentifier();
            parsedLine = putId(buttonId, line);
            generatedIds.add(buttonId);
        }

        String buttonOnClickValue = extractOnClickValueFrom(line);
        parsedLine = removeOnClickFrom(parsedLine);

        parsedLine += ";document.getElementById(\"" + buttonId + "\").onclick = () => " + buttonOnClickValue; // TODO: apos final das tags - n pd ser no meio da string

        if (hasThis(line)) {
            parsedLine = parsedLine.replace("this", "document.getElementById(\"" + buttonId + "\")");
        }

        return parsedLine;
        */
    }

    private boolean isOnClickInsideATag(String line) {
        Pattern pattern = Pattern.compile("([^=]+|)<[^=]+.+onclick=.+[^=]+>([^=]+|)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    private boolean hasId(InlineEventTag tag) {
        return tag.getCode().matches(".*([^A-z]+|)id=.*");
    }

    private String extractIdFrom(InlineEventTag tag) {
        return extractValueFromAttribute("id", tag.getCode());
    }

    private String extractValueFromAttribute(String attribute, String line) {
        Pattern pattern = Pattern.compile(attribute + "=\"[^\"]+\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return line;
        }

        String rawId = matcher.group();

        return removeDoubleQuotes(rawId.split("=")[1]);
    }

    private String removeDoubleQuotes(String text) {
        return text.replace("\"", "");
    }

    private String generateUniqueIdentifier() {
        return ("_" + encryptor.encrypt(String.valueOf(generateRandomNumber())));
    }
	
	private double generateRandomNumber() {
		return (new Date().getTime() + (Math.random() * 9999 + 1));
	}

    private void putId(String id, InlineEventTag tag) {
        StringBuilder parsedLine = new StringBuilder();
        int indexOfTagEnd = tag.getCode().indexOf(">");

        parsedLine.append(tag.getCode().substring(0, indexOfTagEnd));
        parsedLine.append(' ');
        parsedLine.append("id=\"");
        parsedLine.append(id);
        parsedLine.append('\"');
        parsedLine.append(tag.getCode().substring(indexOfTagEnd));

        tag.setCode(parsedLine.toString());
    }

    private String extractOnClickValueFrom(InlineEventTag tag) {
        String value = extractValueFromAttribute("onclick", tag.getCode());

        if (hasTemplateStringTokens(value)) {
            int indexOfParametersBegins = value.indexOf("(");
            int indexOfParametersEnds = value.lastIndexOf(")");
            
            value = value.substring(0, indexOfParametersBegins+1) 
                + "`" + value.substring(indexOfParametersBegins+1, indexOfParametersEnds) + "`" 
                + value.substring(indexOfParametersEnds);
            //value = "`" + value + "`";
        }

        return value;
    }

    private boolean hasTemplateStringTokens(String line) {
        return line.matches(".*\\$\\{.+\\}.*");
    }

    private void removeOnClickFrom(InlineEventTag tag) {
        Pattern pattern = Pattern.compile("onclick=\"[^\"]+\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tag.getCode());

        if (matcher.find()) {
            tag.setCode(matcher.replaceFirst(""));
        }
    }

    private boolean hasThis(InlineEventTag tag) {
        return tag.getCode().matches(".*([^A-z]+|)this([^A-z]+|).*");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getParsedCode() {
        return parsedCode;
    }

    List<String> getGeneratedIds() {
        return generatedIds;
    }
}
