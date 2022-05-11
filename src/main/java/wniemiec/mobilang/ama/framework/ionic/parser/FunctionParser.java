package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;


class FunctionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> convertedCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public FunctionParser() {
        convertedCode = new ArrayList<>();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(List<String> code) {
        if ((code == null) || code.isEmpty()) {
            return;
        }

        // TODO: compatibility with multi-line parameters
        convertedCode = new ArrayList<>();

        for (String line : code) {
            String parsedLine = line;

            if (line.matches(".*([\\s\\t]+|)function([\\s\\t]+).*")) {
                parsedLine = line.replaceAll("function([\\s\\t]+)", "const ");

                int idxParametersBegin = parsedLine.indexOf("(");
                int idxParametersEnd = parsedLine.lastIndexOf(")");
                parsedLine = 
                parsedLine.substring(0, idxParametersBegin)
                    + " = "
                    + parsedLine.substring(idxParametersBegin, idxParametersEnd + 1)
                    + " => " 
                    + parsedLine.substring(idxParametersEnd + 1);
            }

            convertedCode.add(parsedLine);
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getConvertedCode() {
        return convertedCode;
    }
}
