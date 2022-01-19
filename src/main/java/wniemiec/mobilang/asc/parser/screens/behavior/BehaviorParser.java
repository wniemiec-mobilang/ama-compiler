package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class BehaviorParser  {

    private String contentNode;

    public BehaviorParser(SortedMap<String, List<Node>> tree, Node behaviorNode) {
        contentNode = tree.get(behaviorNode.getId()).get(0).getLabel();
    }

    
    public Behavior parse() throws ParseException {
        //System.out.println("-----< BEHAVIOR PARSER >-----");
        //System.out.println(contentNode);
        //System.out.println("-------------------------------\n");

        List<Instruction> code = parseJson(contentNode);
        Behavior behavior = new Behavior(code);

        //behavior.print();

        return behavior;
    }

    private List<Instruction> parseJson(String json) throws ParseException {
        List<Instruction> instructions = new ArrayList<>();
        JSONObject obj = new JSONObject(json);
        
        JSONArray programBody = obj
            .getJSONArray("body");

        for (int i = 0; i < programBody.length(); i++) {
            parseBodyLine(programBody.getJSONObject(i));

            Instruction instruction = parseBodyLine(programBody.getJSONObject(i));
            instructions.add(instruction);
        }

        return instructions;
    }


    private Instruction parseBodyLine(JSONObject bodyLine) {
        Instruction instruction = null;
        String type = bodyLine.getString("type");

        if (type.equals("ExpressionStatement")) {
            instruction = parseBodyExpressionStatement(bodyLine);
        }
        else if (type.equals("ClassDeclaration")) {
            instruction = parseBodyClassDeclaration(bodyLine);
        }
        else if (type.equals("VariableDeclaration")) {
            instruction = parseBodyVariableDeclaration(bodyLine);
        }

        return instruction;
    }


    private Instruction parseBodyExpressionStatement(JSONObject bodyLine) {
        return new Statement(
            "ExpressionStatement",
            parseExpression(bodyLine.getJSONObject("expression"))
        );
    }


    private Expression parseExpression(JSONObject jsonObject) {
        Expression expression = null;
        String expressionType = jsonObject.getString("type");

        if (expressionType.equals("AssignmentExpression") || expressionType.equals("BinaryExpression")) {
            expression = new AssignmentExpression(
                jsonObject.getString("operator"),
                parseExpression(jsonObject.getJSONObject("left")),
                parseExpression(jsonObject.getJSONObject("right"))
            );
        }
        else if (expressionType.equals("MemberExpression")) {
            JSONObject property = jsonObject.getJSONObject("property");
            //Object value = property.get("")

            expression = new MemberExpression(
                parseExpression(jsonObject.getJSONObject("object")),
                property.getString("type"),
                property.optString("name", ""),
                property.optInt("value", -99),
                jsonObject.getBoolean("computed"),
                jsonObject.getBoolean("optional")
            );
        }
        else if (expressionType.equals("CallExpression")) {
            expression = new CallExpression(
                parseExpression(jsonObject.getJSONObject("callee")),
                parseExpressions(jsonObject.getJSONArray("arguments"))
            );
        }
        else if (expressionType.equals("ArrowFunctionExpression")) {
            expression = new ArrowFunctionExpression(
                jsonObject.getBoolean("async"),
                parseExpressions(jsonObject.getJSONArray("params")),
                parseExpression(jsonObject.getJSONObject("body"))
            );
        }
        else if (expressionType.equals("ArrayExpression")) {
            expression = new ArrayExpression(
                parseExpressions(jsonObject.getJSONArray("elements"))
            );
        }
        else if (expressionType.equals("ObjectExpression")) {
            expression = new ObjectExpression(
                parseProperties(jsonObject.getJSONArray("properties"))
            );
        }
        else if (expressionType.equals("TemplateLiteral")) {
            return new TemplateLiteral(
                parseExpressions(jsonObject.getJSONArray("expressions")),
                parseExpressions(jsonObject.getJSONArray("quasis"))
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
            //if ()
        }

        return expression;
    }


    private Map<String, Expression> parseProperties(JSONArray jsonProperties) {
        Map<String, Expression> properties = new HashMap<>();

        for (int i = 0; i < jsonProperties.length(); i++) {
            JSONObject property = jsonProperties.getJSONObject(i);
            
            properties.put(
                property.getJSONObject("key").getString("name"), 
                parseExpression(property.getJSONObject("value"))
            );
            //expressions.add(parseExpression(jsonProperties.getJSONObject(i)));
        }

        return properties;
    }


    private List<Expression> parseExpressions(JSONArray jsonExpressions) {
        List<Expression> expressions = new ArrayList<>();

        for (int i = 0; i < jsonExpressions.length(); i++) {
            expressions.add(parseExpression(jsonExpressions.getJSONObject(i)));
        }

        return expressions;
    }

    private Instruction parseBodyClassDeclaration(JSONObject bodyLine) {
        return null;
    }


    private Instruction parseBodyVariableDeclaration(JSONObject bodyLine) {
        return new Declaration(
            bodyLine.getString("type"), 
            bodyLine.getString("kind"), 
            parseDeclarations(bodyLine.getJSONArray("declarations"))
        );
    }


    private List<Declarator> parseDeclarations(JSONArray jsonDeclarations) {
        List<Declarator> declarations = new ArrayList<>();

        for (int i = 0; i < jsonDeclarations.length(); i++) {
            declarations.add(parseDeclarator(jsonDeclarations.getJSONObject(i)));
        }

        return declarations;
    }


    private Declarator parseDeclarator(JSONObject jsonDeclarator) {
        return new Declarator(
            jsonDeclarator.getString("type"),
            jsonDeclarator.getJSONObject("id").getString("type"),
            jsonDeclarator.getJSONObject("id").getString("name"),
            parseExpression(jsonDeclarator.getJSONObject("init"))
        );
    }
}
