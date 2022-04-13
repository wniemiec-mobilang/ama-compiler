package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.Expression;
import wniemiec.mobilang.ama.models.behavior.Identifier;
import wniemiec.mobilang.ama.parser.exception.ParseException;


/**
 * Responsible for parsing identifiers from behavior node from MobiLang AST.
 */
class IdentifierExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static IdentifierExpressionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private IdentifierExpressionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static IdentifierExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new IdentifierExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new Identifier(jsonObject.getString("name"));

    }
    
}
