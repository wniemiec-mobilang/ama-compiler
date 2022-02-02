package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.Identifier;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class IdentifierExpressionJsonParser implements ExpressionJsonParser {

    private static IdentifierExpressionJsonParser instance;
    private ExpressionParser expressionParser;

    private IdentifierExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }

    public static IdentifierExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new IdentifierExpressionJsonParser();
        }

        return instance;
    }
    
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new Identifier(jsonObject.getString("name"));

    }
    
}
