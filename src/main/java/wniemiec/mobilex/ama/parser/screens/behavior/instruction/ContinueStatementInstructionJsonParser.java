package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.ContinueStatement;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


class ContinueStatementInstructionJsonParser implements InstructionJsonParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ContinueStatementInstructionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ContinueStatementInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ContinueStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ContinueStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ContinueStatement(
            jsonObject.isNull("label") 
                ? null 
                : expressionParser.parse(jsonObject.getJSONObject("label"))
        );
    }
}
