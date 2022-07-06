package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.TemplateElement;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing template elements from behavior node from MobiLang 
 * AST.
 */
class TemplateElementExpressionJsonParser implements ExpressionJsonParser {

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
