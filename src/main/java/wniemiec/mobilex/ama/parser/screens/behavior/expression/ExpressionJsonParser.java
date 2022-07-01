package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing an expression from code of behavior node from MobiLang 
 * AST.
 */
interface ExpressionJsonParser {
    
    Expression parse(JSONObject jsonObject) throws JSONException, ParseException;
}
