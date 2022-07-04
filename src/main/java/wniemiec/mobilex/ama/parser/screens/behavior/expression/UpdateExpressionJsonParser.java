package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.UpdateExpression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing update expressions from behavior node from MobiLang 
 * AST.
 */
class UpdateExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static UpdateExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private UpdateExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static UpdateExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new UpdateExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new UpdateExpression(
            jsonObject.getString("operator"),
            jsonObject.getBoolean("prefix"),
            expressionParser.parse(jsonObject.getJSONObject("argument"))
        );
    }
    
}
