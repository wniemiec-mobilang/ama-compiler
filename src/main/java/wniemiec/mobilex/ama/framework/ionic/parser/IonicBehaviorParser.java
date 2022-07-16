package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.util.data.Validator;


public class IonicBehaviorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final IonicMobilangDirectiveParser directiveParser;
    private final StyleParser styleParser;
    private final EventBehaviorParser eventParser;
    private List<String> parsedCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicBehaviorParser() {
        parsedCode = new ArrayList<>();
        directiveParser = new IonicMobilangDirectiveParser();
        styleParser = new StyleParser();
        eventParser = new EventBehaviorParser();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(Behavior behavior) {
        Validator.validateBehavior(behavior);

        parsedCode = behavior.toCode();
        runStyleParser();
        runEventParser();
        runDirectiveParser();
    }

    private void runStyleParser() {  
        styleParser.parse(parsedCode);
        parsedCode = styleParser.getParsedCode();
    }

    private void runEventParser() {  
        eventParser.parse(parsedCode);
        parsedCode = eventParser.getParsedCode();
    }

    private void runDirectiveParser() {
        directiveParser.parse(parsedCode);
        parsedCode = directiveParser.getParsedCode();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getParsedCode() {
        return parsedCode;
    }

    List<String> getGeneratedIds() {
        return eventParser.getGeneratedIds();
    }
}
