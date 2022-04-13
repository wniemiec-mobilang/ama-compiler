package wniemiec.mobilang.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.FunctionDeclaration;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing function declarations from behavior node from 
 * MobiLang AST.
 */
class FunctionDeclarationInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static FunctionDeclarationInstructionJsonParser instance;
    private final InstructionParser instructionParser;
    private final ExpressionParser expressionParser;


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
