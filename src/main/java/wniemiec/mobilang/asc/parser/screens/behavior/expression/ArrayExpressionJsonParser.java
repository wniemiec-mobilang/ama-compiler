package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.ArrayExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing array expressions from behavior node from MobiLang 
 * AST.
 */
class ArrayExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrayExpressionJsonParser instance;
    private ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ArrayExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ArrayExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ArrayExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ArrayExpression(
            expressionParser.parse(jsonObject.getJSONArray("elements"))
        );   
    }
}
