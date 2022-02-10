package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.exception.FactoryException;


/**
 * Responsible for parsing expressions from behavior node from MobiLang AST.
 */
public class ExpressionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ExpressionParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ExpressionParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ExpressionParser getInstance() {
        if (instance == null) {
            instance = new ExpressionParser();
        }

        return instance;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<Expression> parse(JSONArray jsonExpressions) 
    throws JSONException, ParseException {
        List<Expression> expressions = new ArrayList<>();

        for (int i = 0; i < jsonExpressions.length(); i++) {
            expressions.add(parse(jsonExpressions.getJSONObject(i)));
        }

        return expressions;
    }

    public Expression parse(JSONObject jsonObject) 
    throws ParseException {
        String expressionType = extractExpressionType(jsonObject);
        
        try {
            return ExpressionJsonParserFactory
                .get(expressionType)
                .parse(jsonObject);
        } 
        catch (FactoryException e) {
            throw new ParseException("Behavior - type not supported: " + expressionType);
        }
    }

    private String extractExpressionType(JSONObject jsonObject) {
        return jsonObject.getString("type");
    }
}
