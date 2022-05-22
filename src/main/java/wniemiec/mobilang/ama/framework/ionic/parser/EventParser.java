package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.data.java.Encryptor;
import wniemiec.data.java.Encryptors;


class EventParser {


    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Encryptor encryptor;
    private List<String> parsedCode;
    private List<String> generatedIds;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public EventParser() {
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

        if (hasOnClick(line)) {
            parsedLine = parseOnClick(line);
        }

        return parsedLine;
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
    private String parseOnClick(String line) {
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

        parsedLine += ";document.getElementById(\"" + buttonId + "\").onclick = () => " + buttonOnClickValue;

        if (hasThis(line)) {
            parsedLine = parsedLine.replace("this", "document.getElementById(\"" + buttonId + "\")");
        }

        return parsedLine;
    }

    private boolean isOnClickInsideATag(String line) {
        Pattern pattern = Pattern.compile("([^=]+|)<[^=]+.+onclick=.+[^=]+>([^=]+|)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    private boolean hasId(String line) {
        return line.matches(".*([^A-z]+|)id=.*");
    }

    private String extractIdFrom(String line) {
        return extractValueFromAttribute("id", line);
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

    private String putId(String id, String line) {
        StringBuilder parsedLine = new StringBuilder();
        int indexOfTagEnd = line.indexOf(">");

        parsedLine.append(line.substring(0, indexOfTagEnd));
        parsedLine.append(' ');
        parsedLine.append("id=\"");
        parsedLine.append(id);
        parsedLine.append('\"');
        parsedLine.append(line.substring(indexOfTagEnd));

        return parsedLine.toString();
    }

    private String extractOnClickValueFrom(String line) {
        return extractValueFromAttribute("onclick", line);
    }

    private String removeOnClickFrom(String line) {
        Pattern pattern = Pattern.compile("onclick=\"[^\"]+\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return line;
        }

        return matcher.replaceFirst("");
    }

    private boolean hasThis(String line) {
        return line.matches(".*([^A-z]+|)this([^A-z]+|).*");
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
