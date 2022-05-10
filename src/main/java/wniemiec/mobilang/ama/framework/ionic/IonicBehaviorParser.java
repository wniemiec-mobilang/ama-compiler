package wniemiec.mobilang.ama.framework.ionic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import wniemiec.io.java.BabelTranspiler;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.framework.ionic.parser.ArrowFunctionConverter;
import wniemiec.mobilang.ama.models.behavior.Behavior;


class IonicBehaviorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final BabelTranspiler babelTranspiler;
    private final IonicMobiLangDirectiveParser directiveParser;
    private List<String> parsedCode;
    private Behavior behavior;
    private List<String> babelErrorLog;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicBehaviorParser(Behavior behavior) {
        this.behavior = behavior;
        babelErrorLog = new ArrayList<>();
        parsedCode = new ArrayList<>();
        babelTranspiler = new BabelTranspiler(babelErrorLog::add);
        directiveParser = new IonicMobiLangDirectiveParser();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> parse() throws CoderException {
        runBabel();
        runFunctionProcessor();
        runDirectiveParser();

        return parsedCode;
    }

    private void runBabel() throws CoderException {
        parsedCode = runBabelTranspiler(behavior.toCode());

        babelErrorLog = babelErrorLog
            .stream()
            .filter(message -> !message.contains("npm notice"))
            .collect(Collectors.toList());

        if (!babelErrorLog.isEmpty()) {
            throw new CoderException(babelErrorLog);
        }
    }

    private List<String> runBabelTranspiler(List<String> code) 
    throws CoderException {
        try {
            return babelTranspiler.fromCode(code);
        } 
        catch (IOException e) {
            throw new CoderException(e.getMessage());
        }
    }
    
    private void runFunctionProcessor() {  
        ArrowFunctionConverter functionConverter = new ArrowFunctionConverter();

        functionConverter.run(parsedCode);
        parsedCode = functionConverter.getConvertedCode();
    }

    private void runDirectiveParser() {
        parsedCode = directiveParser.parse(parsedCode);
    }
}
