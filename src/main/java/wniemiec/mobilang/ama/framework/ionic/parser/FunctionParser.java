package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;


class FunctionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> parsedCode;


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
        if ((code == null) || code.isEmpty()) {
            return;
        }

        parsedCode = new ArrayList<>();

        for (String line : code) {
            parsedCode.add(parseLine(line));
        }
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
        return line.matches(".*([\\s\\t]+|)function([\\s\\t]+).*");
    }

    private String convertFunctionToArrowFunction(String line) {
        StringBuilder parsedLine = new StringBuilder();
        int idxParametersBegin = line.indexOf("(");
        int idxParametersEnd = line.lastIndexOf(")");
        String functionHeader = line.substring(0, idxParametersBegin);

        parsedLine.append(functionHeader.replaceAll("function([\\s\\t]+)", "const "));
        parsedLine.append(" = ");
        parsedLine.append(line.substring(idxParametersBegin, idxParametersEnd + 1));
        parsedLine.append(line.substring(idxParametersEnd + 1));

        return parsedLine.toString();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getParsedCode() {
        return parsedCode;
    }
}
