package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.AssignmentExpression;
import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing assignment patterns from behavior node from MobiLang 
 * AST.
 */
class AssignmentPatternExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static AssignmentPatternExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private AssignmentPatternExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static AssignmentPatternExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new AssignmentPatternExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new AssignmentExpression(
            "=",
            expressionParser.parse(jsonObject.getJSONObject("left")),
            expressionParser.parse(jsonObject.getJSONObject("right"))
        );
    }
}