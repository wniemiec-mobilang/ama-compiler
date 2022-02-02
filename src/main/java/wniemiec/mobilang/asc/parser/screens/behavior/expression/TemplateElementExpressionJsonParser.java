package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.TemplateElement;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing template elements from behavior node from MobiLang 
 * AST.
 */
public class TemplateElementExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static TemplateElementExpressionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private TemplateElementExpressionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static TemplateElementExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new TemplateElementExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new TemplateElement(
            jsonObject.getJSONObject("value").getString("raw"),
            jsonObject.getBoolean("tail")
        );
    }
    
}
