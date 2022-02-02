package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public interface ExpressionJsonParser {
    
    Expression parse(JSONObject jsonObject) throws JSONException, ParseException;
}
