package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.ForOfDeclaration;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing for of statements from behavior node from MobiLang 
 * AST.
 */
class ForOfStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ForOfStatementInstructionJsonParser instance;
    private final InstructionParser instructionParser;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ForOfStatementInstructionJsonParser() {
        instructionParser = InstructionParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ForOfStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ForOfStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ForOfDeclaration(
            instructionParser.parse(jsonObject.getJSONObject("left")), 
            expressionParser.parse(jsonObject.getJSONObject("right")), 
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
