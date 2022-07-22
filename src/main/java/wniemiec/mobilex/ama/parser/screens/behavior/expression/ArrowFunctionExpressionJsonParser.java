package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.ArrowFunctionExpression;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.instruction.InstructionParser;


/**
 * Responsible for parsing arrow functions from behavior node from Mobilang 
 * AST.
 */
class ArrowFunctionExpressionJsonParser implements ExpressionJsonParser{

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrowFunctionExpressionJsonParser instance;
    private final ExpressionParser expressionParser;
    private final InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected ArrowFunctionExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
        instructionParser = InstructionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ArrowFunctionExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ArrowFunctionExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        if (!isBlockStatement(jsonObject.getJSONObject("body"))) {
            return new ArrowFunctionExpression(
                jsonObject.getBoolean("async"),
                expressionParser.parse(jsonObject.getJSONArray("params")),
                new ExpressionStatement(expressionParser.parse(jsonObject.getJSONObject("body")))
            );
        }

        return new ArrowFunctionExpression(
            jsonObject.getBoolean("async"),
            expressionParser.parse(jsonObject.getJSONArray("params")),
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }


    private boolean isBlockStatement(JSONObject jsonObject) {
        return jsonObject.get("type").equals("BlockStatement");
    }
}
