package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.models.behavior.MemberExpression;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing member expressions from behavior node from MobiLang 
 * AST.
 */
class MemberExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static MemberExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private MemberExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static MemberExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new MemberExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        JSONObject property = jsonObject.getJSONObject("property");

        return new MemberExpression(
            expressionParser.parse(jsonObject.getJSONObject("object")),
            property.getString("type"),
            property.optString("name", ""),
            property.optInt("value", -99),
            jsonObject.getBoolean("computed"),
            jsonObject.getBoolean("optional")
        );
    }
}
