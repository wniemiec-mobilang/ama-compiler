package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import wniemiec.mobilang.ama.models.tag.Tag;


public class IonicStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Tag structure;
    private final List<String> inputFields;
    private final List<String> parsedCode;
    private final IonicMobiLangDirectiveParser directiveParser;
    private final InputTagParser inputParser;
    private Tag parsedStructure;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicStructureParser(Tag structure) {
        this.structure = structure;
        inputFields = new ArrayList<>();
        parsedCode = new ArrayList<>();
        directiveParser = new IonicMobiLangDirectiveParser();
        inputParser = new InputTagParser();
        parsedStructure = Tag.getEmptyInstance();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> parse() {
        runInputProcessor();
        runDirectiveParser();

        return parsedCode;
    }

    private void runInputProcessor() {
        Stack<Tag> toParse = new Stack<>();
        
        parsedStructure = structure.clone();
        toParse.add(parsedStructure);

        while (!toParse.isEmpty()) {
            Tag currentTag = toParse.pop();
    
            inputParser.parse(currentTag);
            Tag parsedTag = inputParser.getParsedTag();

            currentTag.setAttributes(parsedTag.getAttributes());
            currentTag.setName(parsedTag.getName());
            currentTag.setStyle(parsedTag.getStyle());
            currentTag.setValue(parsedTag.getValue());

            currentTag.getChildren().forEach(toParse::push);
        }
    }

    private void runDirectiveParser() {
        directiveParser.parse(parsedStructure.toCode());
        parsedCode.addAll(directiveParser.getParsedCode());
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputFields;
    }
}
