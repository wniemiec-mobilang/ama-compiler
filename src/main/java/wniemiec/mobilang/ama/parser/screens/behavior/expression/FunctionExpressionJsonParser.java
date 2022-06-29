package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.models.behavior.ExpressionStatement;
import wniemiec.mobilang.ama.models.behavior.FunctionExpression;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.parser.screens.behavior.instruction.InstructionParser;


/**
 * Responsible for parsing function expressions from behavior node from 
 * MobiLang AST.
 */
class FunctionExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static FunctionExpressionJsonParser instance;
    private final ExpressionParser expressionParser;
    private final InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected FunctionExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
        instructionParser = InstructionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static FunctionExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new FunctionExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        if (!jsonObject.getJSONObject("body").get("type").equals("BlockStatement")) {
            return new FunctionExpression(
                jsonObject.getBoolean("async"),
                expressionParser.parse(jsonObject.getJSONArray("params")),
                new ExpressionStatement(expressionParser.parse(jsonObject.getJSONObject("body")))
            );
        }

        return new FunctionExpression(
            jsonObject.getBoolean("async"),
            expressionParser.parse(jsonObject.getJSONArray("params")),
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
