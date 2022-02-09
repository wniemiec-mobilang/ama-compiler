package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.util.List;
import java.util.Map;
import wniemiec.mobilang.asc.models.Variable;


/**
 * Responsible for parsing declaration code from behavior code on React Native 
 * framework.
 */
class DeclarationReactNativeParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> stateBody;
    private Map<String, String> symbolTable;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public DeclarationReactNativeParser(
        List<String> stateBody, 
        Map<String, String> symbolTable
    ) {
        this.symbolTable = symbolTable;
        this.stateBody = stateBody;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parseDeclaration(String code) {
        parseTagIdentifier(code, code);
    }

    private void parseTagIdentifier(String tagId, String code) {
        String varName = extractIdentifierFromDeclaration(code);

        symbolTable.put(varName, tagId);
    }

    private String extractIdentifierFromDeclaration(String code) {
        return code.split(" ")[1].split("=")[0];
    }

    public void parseDeclarations(List<Variable> stateDeclarations) {
        for (Variable declaration : stateDeclarations) {
            stateBody.add(buildDeclarationCode(declaration));
        }
    }

    private String buildDeclarationCode(Variable declaration) {
        StringBuilder code = new StringBuilder();

        code.append("set");
        code.append(declaration.getId());
        code.append("(_");
        code.append(declaration.getId());
        code.append(')');

        return code.toString();
    }   
}
