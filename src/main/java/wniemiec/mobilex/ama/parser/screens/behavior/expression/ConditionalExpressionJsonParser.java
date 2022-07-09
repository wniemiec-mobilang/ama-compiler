package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.ConditionalExpression;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing conditional expressions from behavior node from 
 * MobiLang AST.
 */
class ConditionalExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ConditionalExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ConditionalExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ConditionalExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ConditionalExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new ConditionalExpression(
            expressionParser.parse(jsonObject.getJSONObject("test")),
            expressionParser.parse(jsonObject.getJSONObject("consequent")),
            expressionParser.parse(jsonObject.getJSONObject("alternate"))
        );
    }
}
