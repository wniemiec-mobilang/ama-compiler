package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.ObjectExpression;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing object expressions from behavior node from MobiLang 
 * AST.
 */
public class ObjectExpressionJsonParser implements ExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ObjectExpressionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ObjectExpressionJsonParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ObjectExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ObjectExpressionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Expression parse(JSONObject jsonObject) throws JSONException, ParseException {
        return new ObjectExpression(
            parseProperties(jsonObject.getJSONArray("properties"))
        );
    }
    
    private Map<String, Expression> parseProperties(JSONArray jsonProperties) 
    throws JSONException, ParseException {
        Map<String, Expression> properties = new HashMap<>();

        for (int i = 0; i < jsonProperties.length(); i++) {
            JSONObject property = jsonProperties.getJSONObject(i);
            
            properties.put(
                property.getJSONObject("key").getString("name"), 
                parse(property.getJSONObject("value"))
            );
        }

        return properties;
    }
}
