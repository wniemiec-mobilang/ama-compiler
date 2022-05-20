package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;


class FunctionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String ARROW_FUNCTION_CONNECTOR;
    private List<String> parsedCode;


    //-------------------------------------------------------------------------
    //		Initialization blocks
    //-------------------------------------------------------------------------
    static {
        ARROW_FUNCTION_CONNECTOR = " => ";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public FunctionParser() {
        parsedCode = new ArrayList<>();
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
        // TODO: compatibility with multi-line parameters
        String parsedLine = line;

        if (hasFunction(line)) {
            parsedLine = convertFunctionToArrowFunction(line);
        }

        return parsedLine;
    }

    private boolean hasFunction(String line) {
        return line.matches(".*([ \\t]+|)function([ \\t]+).*");
    }

    private String convertFunctionToArrowFunction(String line) {
        StringBuilder parsedLine = new StringBuilder();
        
        if (belongsToAVariable(line)) {
            parsedLine.append(extractFunctionHeaderFromVariable(line));
        }
        else {
            parsedLine.append(extractFunctionHeader(line));
        }
        
        parsedLine.append(extractFunctionParameters(line));
        parsedLine.append(ARROW_FUNCTION_CONNECTOR);
        parsedLine.append(extractFunctionBody(line));

        return parsedLine.toString();
    }

    private String extractFunctionHeaderFromVariable(String line) {
        return line.substring(0, getIndexOfFunctionBegin(line));
    }

    private int getIndexOfFunctionBegin(String line) {
        return line.indexOf("function");
    }

    private String extractFunctionHeader(String line) {
        StringBuilder header = new StringBuilder();
        int indexOfParametersBegin = line.indexOf("(", getIndexOfFunctionBegin(line));
        String functionHeader = line.substring(0, indexOfParametersBegin);
        
        header.append(functionHeader.replaceAll("function([ \\t]+)", "const "));
        header.append(" = ");

        return header.toString();
    }

    private String extractFunctionBody(String line) {
        int indexOfParametersEnd = line.indexOf(")", getIndexOfFunctionBegin(line));

        return line.substring(indexOfParametersEnd + 1);
    }

    private String extractFunctionParameters(String line) {
        int indexOfFunction = getIndexOfFunctionBegin(line);
        int indexOfParametersBegin = line.indexOf("(", indexOfFunction);
        int indexOfParametersEnd = line.indexOf(")", indexOfFunction);

        return line.substring(indexOfParametersBegin, indexOfParametersEnd + 1);
    }

    private boolean belongsToAVariable(String line) {
        return line.matches("[^{]+=[^>]+.*");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getParsedCode() {
        return parsedCode;
    }
}
