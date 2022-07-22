package wniemiec.mobilex.ama.parser.screens.behavior.expression;


import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.UnaryExpression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing unary expressions from behavior node from Mobilang 
 * AST.
 */
public class UnaryExpressionJsonParser implements ExpressionJsonParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static UnaryExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private UnaryExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static UnaryExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new UnaryExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new UnaryExpression(
            jsonObject.getString("operator"),
            jsonObject.getBoolean("prefix"),
            expressionParser.parse(jsonObject.getJSONObject("argument"))
        );
    }
}
