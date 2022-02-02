package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.TemplateLiteral;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing template literals from behavior node from MobiLang 
 * AST.
 */
public class TemplateLiteralExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static TemplateLiteralExpressionJsonParser instance;
    private ExpressionParser expressionParser;


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
