package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing an expression from code of behavior node from MobiLang 
 * AST.
 */
interface ExpressionJsonParser {
    
    Expression parse(JSONObject jsonObject) throws JSONException, ParseException;
}
