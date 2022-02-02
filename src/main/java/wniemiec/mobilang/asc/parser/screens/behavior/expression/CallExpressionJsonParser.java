package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.CallExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class CallExpressionJsonParser implements ExpressionJsonParser {

    private static CallExpressionJsonParser instance;
    private ExpressionParser expressionParser;

    private CallExpressionJsonParser() {
    }

    public static CallExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new CallExpressionJsonParser();
        }

        return instance;
    }
    
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new CallExpression(
            expressionParser.parse(jsonObject.getJSONObject("callee")),
            expressionParser.parse(jsonObject.getJSONArray("arguments"))
        );
    }
    
}
