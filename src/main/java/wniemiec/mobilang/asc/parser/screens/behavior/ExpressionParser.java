package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.ArrayExpression;
import wniemiec.mobilang.asc.models.behavior.ArrowFunctionExpression;
import wniemiec.mobilang.asc.models.behavior.AssignmentExpression;
import wniemiec.mobilang.asc.models.behavior.CallExpression;
import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.models.behavior.Identifier;
import wniemiec.mobilang.asc.models.behavior.Literal;
import wniemiec.mobilang.asc.models.behavior.MemberExpression;
import wniemiec.mobilang.asc.models.behavior.ObjectExpression;
import wniemiec.mobilang.asc.models.behavior.TemplateElement;
import wniemiec.mobilang.asc.models.behavior.TemplateLiteral;
import wniemiec.mobilang.asc.models.behavior.UpdateExpression;
import wniemiec.mobilang.asc.parser.exception.ParseException;

class ExpressionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ExpressionParser instance;
    private InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ExpressionParser() {
        instructionParser = InstructionParser.getInstance();
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
    public Expression parse(JSONObject jsonObject) 
    throws ParseException {
        Expression expression = null;
        String expressionType = jsonObject.getString("type");

        if (expressionType.equals("AssignmentExpression") || expressionType.equals("BinaryExpression")) {
            expression = new AssignmentExpression(
                jsonObject.getString("operator"),
                parse(jsonObject.getJSONObject("left")),
                parse(jsonObject.getJSONObject("right"))
            );
        }
        else if (expressionType.equals("AssignmentPattern")) {
            expression = new AssignmentExpression(
                "=",
                parse(jsonObject.getJSONObject("left")),
                parse(jsonObject.getJSONObject("right"))
            );
        }
        else if (expressionType.equals("UpdateExpression")) {
            expression = new UpdateExpression(
                jsonObject.getString("operator"),
                jsonObject.getBoolean("prefix"),
                parse(jsonObject.getJSONObject("argument"))
            );
        }
        else if (expressionType.equals("MemberExpression")) {
            JSONObject property = jsonObject.getJSONObject("property");
            //Object value = property.get("")

            expression = new MemberExpression(
                parse(jsonObject.getJSONObject("object")),
                property.getString("type"),
                property.optString("name", ""),
                property.optInt("value", -99),
                jsonObject.getBoolean("computed"),
                jsonObject.getBoolean("optional")
            );
        }
        else if (expressionType.equals("CallExpression")) {
            expression = new CallExpression(
                parse(jsonObject.getJSONObject("callee")),
                parse(jsonObject.getJSONArray("arguments"))
            );
        }
        else if (expressionType.equals("ArrowFunctionExpression")) {
            if (!jsonObject.getJSONObject("body").get("type").equals("BlockStatement")) {
                expression = new ArrowFunctionExpression(
                    jsonObject.getBoolean("async"),
                    parse(jsonObject.getJSONArray("params")),
                    parse(jsonObject.getJSONObject("body"))
                );
            }
            else {
                expression = new ArrowFunctionExpression(
                    jsonObject.getBoolean("async"),
                    parse(jsonObject.getJSONArray("params")),
                    instructionParser.parse(jsonObject.getJSONObject("body"))
                );
            }
            
        }
        else if (expressionType.equals("ArrayExpression")) {
            expression = new ArrayExpression(
                parse(jsonObject.getJSONArray("elements"))
            );
        }
        else if (expressionType.equals("ObjectExpression")) {
            expression = new ObjectExpression(
                parseProperties(jsonObject.getJSONArray("properties"))
            );
        }
        else if (expressionType.equals("TemplateLiteral")) {
            return new TemplateLiteral(
                parse(jsonObject.getJSONArray("expressions")),
                parse(jsonObject.getJSONArray("quasis"))
            );
        }
        else if (expressionType.equals("TemplateElement")) {
            expression = new TemplateElement(
                jsonObject.getJSONObject("value").getString("raw"),
                jsonObject.getBoolean("tail")
            );
        }
        else if (expressionType.equals("Identifier")) {
            expression = new Identifier(jsonObject.getString("name"));
        }
        else if (expressionType.equals("Literal")) {
            Object value = jsonObject.get("value");

            if (value instanceof String) {
                expression = new Literal(jsonObject.getString("value"));
            }
            else if (value instanceof Integer) {
                expression = new Literal(String.valueOf(jsonObject.getInt("value")));
            }
            else if (value instanceof Float) {
                expression = new Literal(String.valueOf(jsonObject.getFloat("value")));
            }
            else if (value instanceof Double) {
                expression = new Literal(String.valueOf(jsonObject.getDouble("value")));
            }
        }

        if (expression == null) {
            throw new ParseException("Behavior - type not supported: " + expressionType);
        }

        return expression;
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


    public List<Expression> parse(JSONArray jsonExpressions) 
    throws JSONException, ParseException {
        List<Expression> expressions = new ArrayList<>();

        for (int i = 0; i < jsonExpressions.length(); i++) {
            expressions.add(parse(jsonExpressions.getJSONObject(i)));
        }

        return expressions;
    }
}
