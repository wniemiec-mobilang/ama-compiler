package wniemiec.mobilang.ama.framework.ionic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import wniemiec.io.java.BabelTranspiler;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.models.behavior.Behavior;


class IonicBehaviorParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final BabelTranspiler babelTranspiler;
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
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> run() throws CoderException {
        runBabel();
        runDirectiveProcessor();
        runInputProcessor();

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

    private void runDirectiveProcessor() {
        // TODO: Behavior code dentro de onInit
        throw new NoSuchMethodError("Not implemented yet");
    }

    private void runInputProcessor() {
        // TODO: input.value deve ser substituido por this.input_<id>
        throw new NoSuchMethodError("Not implemented yet");
    }
}
