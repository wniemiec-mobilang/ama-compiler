package wniemiec.mobilex.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Expression;
import wniemiec.mobilex.ama.parser.exception.ParseException;


/**
 * Responsible for parsing arrow functions from behavior node from MobiLang AST.
 */
class ArrowFunctionExpressionJsonParser extends FunctionExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrowFunctionExpressionJsonParser instance;


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
        return super.parse(jsonObject);
    }
}