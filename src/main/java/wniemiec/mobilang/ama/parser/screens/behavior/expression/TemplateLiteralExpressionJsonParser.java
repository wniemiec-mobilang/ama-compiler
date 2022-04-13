package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.models.behavior.TemplateLiteral;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing template literals from behavior node from MobiLang 
 * AST.
 */
class TemplateLiteralExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static TemplateLiteralExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private TemplateLiteralExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static TemplateLiteralExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new TemplateLiteralExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new TemplateLiteral(
                expressionParser.parse(jsonObject.getJSONArray("expressions")),
                expressionParser.parse(jsonObject.getJSONArray("quasis"))
            );
    }
    
}
