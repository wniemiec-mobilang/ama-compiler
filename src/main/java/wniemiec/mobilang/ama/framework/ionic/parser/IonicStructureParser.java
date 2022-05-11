package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import wniemiec.mobilang.ama.models.tag.Tag;


public class IonicStructureParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final IonicMobiLangDirectiveParser directiveParser;
    private final InputTagParser inputParser;
    private Tag parsedStructure;
    private List<String> parsedCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicStructureParser() {
        directiveParser = new IonicMobiLangDirectiveParser();
        inputParser = new InputTagParser();
        parsedCode = new ArrayList<>();
        parsedStructure = Tag.getEmptyInstance();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(Tag structure) {
        setUpParser(structure);
        runInputProcessor();
        runDirectiveParser();
    }

    private Tag setUpParser(Tag structure) {
        return parsedStructure = structure.clone();
    }

    private void runInputProcessor() {
        Stack<Tag> toParse = new Stack<>();
        
        
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
        parsedCode = directiveParser.getParsedCode();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getInputIds() {
        return inputParser.getInputIds();
    }

    public List<String> getParsedCode() {
        return parsedCode;
    }
}
