package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.ArrowFunctionExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.instruction.InstructionParser;


/**
 * Responsible for parsing arrow functions from behavior node from MobiLang AST.
 */
public class ArrowFunctionExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrowFunctionExpressionJsonParser instance;
    private ExpressionParser expressionParser;
    private InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ArrowFunctionExpressionJsonParser() {
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
        if (!jsonObject.getJSONObject("body").get("type").equals("BlockStatement")) {
            return new ArrowFunctionExpression(
                jsonObject.getBoolean("async"),
                expressionParser.parse(jsonObject.getJSONArray("params")),
                expressionParser.parse(jsonObject.getJSONObject("body"))
            );
        }

        return new ArrowFunctionExpression(
            jsonObject.getBoolean("async"),
            expressionParser.parse(jsonObject.getJSONArray("params")),
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
