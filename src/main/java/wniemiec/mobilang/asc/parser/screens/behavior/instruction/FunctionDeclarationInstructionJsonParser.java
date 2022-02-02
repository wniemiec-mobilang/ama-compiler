package wniemiec.mobilang.asc.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.FunctionDeclaration;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing function declarations from behavior node from 
 * MobiLang AST.
 */
class FunctionDeclarationInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static FunctionDeclarationInstructionJsonParser instance;
    private InstructionParser instructionParser;
    private ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private FunctionDeclarationInstructionJsonParser() {
        instructionParser = InstructionParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static FunctionDeclarationInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new FunctionDeclarationInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new FunctionDeclaration(
            jsonObject.getJSONObject("id").getString("name"), 
            jsonObject.getBoolean("async"),
            expressionParser.parse(jsonObject.getJSONArray("params")),
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
