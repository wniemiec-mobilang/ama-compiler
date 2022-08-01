package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.ArrayPattern;
import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing array pattern from behavior node from Mobilang AST.
 */
class ArrayPatternExpressionJsonParser implements ExpressionJsonParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrayPatternExpressionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ArrayPatternExpressionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }
    


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ArrayPatternExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ArrayPatternExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ArrayPattern(
            expressionParser.parse(jsonObject.getJSONArray("elements"))
        );
    }
}
