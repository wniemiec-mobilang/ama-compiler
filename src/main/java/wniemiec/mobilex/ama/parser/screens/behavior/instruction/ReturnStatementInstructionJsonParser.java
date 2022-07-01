package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.ReturnStatement;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing return statements from behavior node from MobiLang 
 * AST.
 */
class ReturnStatementInstructionJsonParser implements InstructionJsonParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ReturnStatementInstructionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ReturnStatementInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ReturnStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ReturnStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        if (!jsonObject.has("argument") || jsonObject.isNull("argument")) {
            return new ReturnStatement(null);    
        }

        return new ReturnStatement(
            expressionParser.parse(jsonObject.getJSONObject("argument"))
        );
    }
}

