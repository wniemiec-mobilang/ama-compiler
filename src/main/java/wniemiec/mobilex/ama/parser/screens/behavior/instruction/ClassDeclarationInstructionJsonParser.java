package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.ClassDeclaration;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing class declarations from behavior node from MobiLang 
 * AST.
 */
class ClassDeclarationInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ClassDeclarationInstructionJsonParser instance;
    private final ExpressionParser expressionParser;
    private final InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ClassDeclarationInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
        instructionParser = InstructionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ClassDeclarationInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ClassDeclarationInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ClassDeclaration(
            expressionParser.parse(jsonObject.getJSONObject("id")), 
            expressionParser.parse(jsonObject.getJSONObject("superClass")), 
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
