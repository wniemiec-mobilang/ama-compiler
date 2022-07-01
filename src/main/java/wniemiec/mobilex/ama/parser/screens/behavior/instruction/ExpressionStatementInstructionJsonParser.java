package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing expression statements from behavior node from 
 * MobiLang AST.
 */
class ExpressionStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ExpressionStatementInstructionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ExpressionStatementInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ExpressionStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ExpressionStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ExpressionStatement(
            expressionParser.parse(jsonObject.getJSONObject("expression"))
        );
    }
}
