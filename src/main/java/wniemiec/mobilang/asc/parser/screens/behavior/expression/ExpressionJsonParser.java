package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing an expression from code of behavior node from MobiLang 
 * AST.
 */
interface ExpressionJsonParser {
    
    Expression parse(JSONObject jsonObject) throws JSONException, ParseException;
}
