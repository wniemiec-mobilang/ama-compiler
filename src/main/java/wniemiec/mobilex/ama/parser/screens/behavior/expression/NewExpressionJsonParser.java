package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.NewExpression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing constructor calls from behavior code from Mobilang 
 * AST. A constructor call is composed of a callee and may have some arguments.
 */
class NewExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static NewExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private NewExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static NewExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new NewExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new NewExpression(
            expressionParser.parse(jsonObject.getJSONObject("callee")),
            expressionParser.parse(jsonObject.getJSONArray("arguments"))
        );
    }
}
