package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.UnaryOperator;
import wniemiec.mobilang.ama.models.EventTag;
import wniemiec.mobilang.ama.models.tag.Tag;


public class IonicStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final IonicMobiLangDirectiveParser directiveParser;
    private final InputTagParser inputParser;
    private final EventTagParser eventParser;
    private Tag parsedStructure;
    private List<String> parsedCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicStructureParser() {
        directiveParser = new IonicMobiLangDirectiveParser();
        inputParser = new InputTagParser();
        eventParser = new EventTagParser();
        parsedCode = new ArrayList<>();
        parsedStructure = Tag.getEmptyInstance();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(Tag structure) {
        validateArgument(structure);
        setUpParser(structure);
        runInputParser();
        runEventParser();
        runDirectiveParser();
    }

    private void validateArgument(Tag structure) {
        if (structure == null) {
            throw new IllegalArgumentException("Structure tag cannot be null");
        }
    }

    private void setUpParser(Tag structure) {
        parsedStructure = structure.clone();
    }

    private void runInputParser() {
        runStructureParser(currentTag -> {
            inputParser.parse(currentTag);
            
            return inputParser.getParsedTag();
        });
    }

    private void runStructureParser(UnaryOperator<Tag> parser) {
        Stack<Tag> toParse = new Stack<>();
        
        toParse.add(parsedStructure);

        while (!toParse.isEmpty()) {
            Tag currentTag = toParse.pop();
            
            parseTag(parser, currentTag);

            currentTag.getChildren().forEach(toParse::push);
        }
    }

    private void parseTag(UnaryOperator<Tag> parser, Tag currentTag) {
        Tag parsedTag = parser.apply(currentTag);

        replaceTagWith(currentTag, parsedTag);
    }

    private void replaceTagWith(Tag base, Tag target) {
        base.setAttributes(target.getAttributes());
        base.setName(target.getName());
        base.setStyle(target.getStyle());
        base.setValue(target.getValue());
        base.setVoidTag(target.isVoidTag());
    }

    private void runEventParser() {
        runStructureParser(currentTag -> {
            eventParser.parse(currentTag);
            
            return eventParser.getParsedTag();
        });
    }

    private void runDirectiveParser() {
        directiveParser.parse(parsedStructure.toCode());
        parsedCode = directiveParser.getParsedCode();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputParser.getInputIds();
    }

    public List<EventTag> getEvents() {
        return eventParser.getEvents();
    }

    public List<String> getParsedCode() {
        return parsedCode;
    }
}
