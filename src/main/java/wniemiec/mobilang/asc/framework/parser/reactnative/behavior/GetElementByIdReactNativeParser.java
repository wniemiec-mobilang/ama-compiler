package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;


/**
 * Responsible for parsing getElementById code from behavior code on React 
 * Native framework.
 */
class GetElementByIdReactNativeParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<Variable> stateDeclarations;
    private Tag structure;
    private Map<String, String> symbolTable;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public GetElementByIdReactNativeParser(
        List<Variable> stateDeclarations, 
        Map<String, String> symbolTable,
        Tag structure
    ) {
        this.symbolTable = symbolTable;
        this.stateDeclarations = stateDeclarations;
        this.structure = structure;
    }
 

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public String parseDeclarationWithGetElementById(String code) {
        String parsedCode = code;
        Pattern pattern = Pattern.compile(".*(getElementById\\(\\\"(.+)\\\"\\)).*");
        Matcher matcher = pattern.matcher(code);

        if (matcher.matches()) {
            String tagId = matcher.group(2);
            Variable stateVariable = buildStateVariable(tagId);

            if (!stateDeclarations.contains(stateVariable)) {
                parseStateVariable(stateVariable);
                parseTagIdentifier(tagId, code);
            }
            
            parsedCode = "";
        }

        return parsedCode;
    }

    private Variable buildStateVariable(String tagId) {
        String normalizedTagId = normalizeIdentifier(tagId);
        
        return new Variable(normalizedTagId, "state", "[]");
    }

    private String normalizeIdentifier(String identifier) {
        return identifier.replace("-", "_");
    }

    private void parseStateVariable(Variable stateVar) {
        stateDeclarations.add(stateVar);
    }

    private void parseTagIdentifier(String tagId, String code) {
        String varName = extractIdentifierFromDeclaration(code);

        symbolTable.put(varName, tagId);
    }

    private String extractIdentifierFromDeclaration(String code) {
        return code.split(" ")[1].split("=")[0];
    }

    public String parseCallFromGetElementById(String code) {
        String attributeAssignment = extractAttributeAssignment(code);
        String attributeName = extractVariableFromAssignment(attributeAssignment);
        String attributeValue = extractValueFromAssignment(attributeAssignment);
        Tag tag = getReferedTag(code);

        tag.addAttribute(attributeName, attributeValue);

        return "";
    }

    private Tag getReferedTag(String code) {
        String tagId = extractIdFromGetElementById(code);
        
        return structure.getTagWithId(tagId);
    }

    private String extractIdFromGetElementById(String line) {
        String id = "";
        Pattern p = Pattern.compile(".*(getElementById\\(\\\"(.+)\\\"\\)).*");
        Matcher m = p.matcher(line);

        if (m.matches()) {
            id = m.group(2);
        }

        return id;
    }

    private String extractAttributeAssignment(String code) {
        return code.substring(code.indexOf(").")+1);
    }

    private String extractVariableFromAssignment(String tagProperty) {
        return tagProperty.split("=")[0];
    }

    private String extractValueFromAssignment(String tagProperty) {
        return tagProperty.split("=")[1];
    }
}
