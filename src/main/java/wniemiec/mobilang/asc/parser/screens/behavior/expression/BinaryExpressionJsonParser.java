package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.AssignmentExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class BinaryExpressionJsonParser implements ExpressionJsonParser {

    private static BinaryExpressionJsonParser instance;
    private ExpressionParser expressionParser;

    private BinaryExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }

    public static BinaryExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new BinaryExpressionJsonParser();
        }

        return instance;
    }
    
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new AssignmentExpression(
            jsonObject.getString("operator"),
            expressionParser.parse(jsonObject.getJSONObject("left")),
            expressionParser.parse(jsonObject.getJSONObject("right"))
        );
    }
}
