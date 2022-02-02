package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.Literal;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing literals from behavior node from MobiLang AST.
 */
class LiteralExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static LiteralExpressionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private LiteralExpressionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static LiteralExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new LiteralExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        Expression expression = null;
        Object value = jsonObject.get("value");

        if (value instanceof String) {
            expression = new Literal(jsonObject.getString("value"));
        }
        else if (value instanceof Integer) {
            expression = new Literal(String.valueOf(jsonObject.getInt("value")));
        }
        else if (value instanceof Float) {
            expression = new Literal(String.valueOf(jsonObject.getFloat("value")));
        }
        else if (value instanceof Double) {
            expression = new Literal(String.valueOf(jsonObject.getDouble("value")));
        }

        return expression;
    }
    
}
