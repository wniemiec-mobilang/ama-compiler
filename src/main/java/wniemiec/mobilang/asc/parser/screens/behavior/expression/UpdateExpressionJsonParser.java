package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.UpdateExpression;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class UpdateExpressionJsonParser implements ExpressionJsonParser {

    private static UpdateExpressionJsonParser instance;
    private ExpressionParser expressionParser;

    private UpdateExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }

    public static UpdateExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new UpdateExpressionJsonParser();
        }

        return instance;
    }
    
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new UpdateExpression(
            jsonObject.getString("operator"),
            jsonObject.getBoolean("prefix"),
            expressionParser.parse(jsonObject.getJSONObject("argument"))
        );
    }
    
}
