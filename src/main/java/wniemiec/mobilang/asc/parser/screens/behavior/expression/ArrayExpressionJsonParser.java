package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.ArrayExpression;
import wniemiec.mobilang.asc.models.behavior.ArrowFunctionExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.InstructionParser;

public class ArrayExpressionJsonParser implements ExpressionJsonParser {

    private static ArrayExpressionJsonParser instance;
    private ExpressionParser expressionParser;

    private ArrayExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }

    public static ArrayExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ArrayExpressionJsonParser();
        }

        return instance;
    }
    
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new ArrayExpression(
            expressionParser.parse(jsonObject.getJSONArray("elements"))
        );   
    }
}
