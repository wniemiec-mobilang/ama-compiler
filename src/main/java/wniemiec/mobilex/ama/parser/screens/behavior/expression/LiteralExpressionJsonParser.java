package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.Literal;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing literals from behavior node from MobiLang AST.
 */
class LiteralExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static LiteralExpressionJsonParser instance;
    private static final String VALUE = "value";


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
        Object value = jsonObject.get(VALUE);

        if (value instanceof String) {
            expression = Literal.ofString(jsonObject.getString(VALUE));
        }
        else if (value instanceof Integer) {
            expression = Literal.ofNumber(String.valueOf(jsonObject.getInt(VALUE)));
        }
        else if (value instanceof Float) {
            expression = Literal.ofNumber(String.valueOf(jsonObject.getFloat(VALUE)));
        }
        else if (value instanceof Double) {
            expression = Literal.ofNumber(String.valueOf(jsonObject.getDouble(VALUE)));
        }

        return expression;
    }
    
}
