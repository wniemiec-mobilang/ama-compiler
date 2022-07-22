package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.BreakStatement;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


class BreakStatementInstructionJsonParser implements InstructionJsonParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static BreakStatementInstructionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private BreakStatementInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static BreakStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new BreakStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new BreakStatement(
            jsonObject.isNull("label") 
                ? null 
                : expressionParser.parse(jsonObject.getJSONObject("label"))
        );
    }
}
