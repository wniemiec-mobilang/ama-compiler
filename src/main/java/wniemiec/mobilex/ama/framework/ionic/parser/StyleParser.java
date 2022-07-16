package wniemiec.mobilex.ama.framework.ionic.parser;

import java.util.ArrayList;
import java.util.List;


class StyleParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> parsedCode;


     //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public StyleParser() {
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
        if (!hasStyleAssignment(line)) {
            return line;
        }

        String parsedLine = line;

        if (hasNumericalAssignment(line)) {
            parsedLine = convertNumericalAssignmentToString(line);
        }

        return parsedLine;
    }

    private boolean hasStyleAssignment(String line) {
        return line.matches(".+\\.style\\..+");
    }

    private boolean hasNumericalAssignment(String line) {
        return line.matches(".+=([^=]+|)[0-9.]+.*");
    }

    private String convertNumericalAssignmentToString(String line) {
        StringBuilder parsedLine = new StringBuilder();
        int indexOfAssignment = line.lastIndexOf("=");
        
        parsedLine.append(line.substring(0, indexOfAssignment+1));

        String value = line.substring(indexOfAssignment+1);

        if (isNumeric(value)) {
            value = value.replaceAll("[\"';]", "");
            if (value.contains("`")) {
                value = "\"" + value.replace("`", "") + "\"`;";
            }
            else {
                value = "\"" + value + "\";";
            }
        }

        parsedLine.append(value);

        return parsedLine.toString();
    }

    private boolean isNumeric(String line) {
        return line.matches("([ \t]+|)['`\"]*[0-9.]+['`\"]*([^A-z]+|)");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getParsedCode() {
        return parsedCode;
    }
}
