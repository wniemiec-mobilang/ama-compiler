package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.Node;
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
        JSONObject obj = new JSONObject(json);
        JSONArray programBody = obj.getJSONArray("body");

        return parseBlockCode(programBody);
    }

    private List<Instruction> parseBlockCode(JSONArray body) throws ParseException {
        List<Instruction> instructions = new ArrayList<>();

        for (int i = 0; i < body.length(); i++) {
            //parseBodyLine(body.getJSONObject(i));

            Instruction instruction = parseInstruction(body.getJSONObject(i));
            instructions.add(instruction);
        }

        return instructions;
    }

    private Instruction parseInstruction(JSONObject bodyLine) throws JSONException, ParseException {
        Instruction instruction = null;
        String type = bodyLine.getString("type");

        if (type.equals("ExpressionStatement")) {
            instruction = parseExpressionStatement(bodyLine);
        }
        else if (type.equals("ClassDeclaration")) {
            instruction = parseClassDeclaration(bodyLine);
        }
        else if (type.equals("VariableDeclaration")) {
            instruction = parseVariableDeclaration(bodyLine);
        }
        else if (type.equals("FunctionDeclaration")) {
            instruction = parseFunctionDeclaration(bodyLine);
        }
        else if (type.equals("ForStatement")) {
            instruction = parseForStatementDeclaration(bodyLine);
        }
        else if (type.equals("IfStatement")) {
            instruction = parseIfStatementDeclaration(bodyLine);
        }
        else if (type.equals("ForOfStatement")) {
            instruction = parseForOfStatementDeclaration(bodyLine);
        }
        else if (type.equals("ForInStatement")) {
            instruction = parseForInStatementDeclaration(bodyLine);
        }
        else if (type.equals("BlockStatement")) {
            instruction = new BlockStatement(parseBlockCode(bodyLine.getJSONArray("body")));
            //parseBodyBlockStatement(bodyLine);
        }
            //    expression = new BlockStatement(
            //        parseExpressions(jsonObject.getJSONArray("body"))
            //    );

        if (instruction == null) {
            throw new ParseException("Behavior - type not supported: " + type);
        }

        return instruction;
    }

    private Instruction parseIfStatementDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new IfStatement(
            parseExpression(bodyLine.getJSONObject("test")), 
            parseInstruction(bodyLine.getJSONObject("consequent"))
        );
    }


    private Instruction parseForStatementDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new ForDeclaration(
            parseInstruction(bodyLine.getJSONObject("init")), 
            parseExpression(bodyLine.getJSONObject("test")), 
            parseExpression(bodyLine.getJSONObject("update")), 
            parseInstruction(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseForOfStatementDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new ForOfDeclaration(
            parseInstruction(bodyLine.getJSONObject("left")), 
            parseExpression(bodyLine.getJSONObject("right")), 
            parseInstruction(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseForInStatementDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new ForInDeclaration(
            parseInstruction(bodyLine.getJSONObject("left")), 
            parseExpression(bodyLine.getJSONObject("right")), 
            parseInstruction(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseFunctionDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new FunctionDeclaration(
            bodyLine.getJSONObject("id").getString("name"), 
            bodyLine.getBoolean("async"),
            parseExpressions(bodyLine.getJSONArray("params")),
            parseInstruction(bodyLine.getJSONObject("body"))
        );
    }


    private Instruction parseExpressionStatement(JSONObject bodyLine) throws JSONException, ParseException {
        return new ExpressionStatement(
            parseExpression(bodyLine.getJSONObject("expression"))
        );
    }


    private Expression parseExpression(JSONObject jsonObject) throws ParseException {
        Expression expression = null;
        String expressionType = jsonObject.getString("type");

        if (expressionType.equals("AssignmentExpression") || expressionType.equals("BinaryExpression")) {
            expression = new AssignmentExpression(
                jsonObject.getString("operator"),
                parseExpression(jsonObject.getJSONObject("left")),
                parseExpression(jsonObject.getJSONObject("right"))
            );
        }
        else if (expressionType.equals("AssignmentPattern")) {
            expression = new AssignmentExpression(
                "=",
                parseExpression(jsonObject.getJSONObject("left")),
                parseExpression(jsonObject.getJSONObject("right"))
            );
        }
        else if (expressionType.equals("UpdateExpression")) {
            expression = new UpdateExpression(
                jsonObject.getString("operator"),
                jsonObject.getBoolean("prefix"),
                parseExpression(jsonObject.getJSONObject("argument"))
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
        //else if (expressionType.equals("ExpressionStatement")) {
        //    expression = parseExpression(jsonObject.getJSONObject("expression"));
        //}
        else if (expressionType.equals("CallExpression")) {
            expression = new CallExpression(
                parseExpression(jsonObject.getJSONObject("callee")),
                parseExpressions(jsonObject.getJSONArray("arguments"))
            );
        }
        else if (expressionType.equals("ArrowFunctionExpression")) {
            if (!jsonObject.getJSONObject("body").get("type").equals("BlockStatement")) {
                expression = new ArrowFunctionExpression(
                    jsonObject.getBoolean("async"),
                    parseExpressions(jsonObject.getJSONArray("params")),
                    parseExpression(jsonObject.getJSONObject("body"))
                );
            }
            else {
                expression = new ArrowFunctionExpression(
                    jsonObject.getBoolean("async"),
                    parseExpressions(jsonObject.getJSONArray("params")),
                    parseInstruction(jsonObject.getJSONObject("body"))
                );
            }
            
        }
        else if (expressionType.equals("ArrayExpression")) {
            expression = new ArrayExpression(
                parseExpressions(jsonObject.getJSONArray("elements"))
            );
        }
        //else if (expressionType.equals("BlockStatement")) {
        //    expression = new BlockStatement(
        //        parseExpressions(jsonObject.getJSONArray("body"))
        //    );
        //}
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

        if (expression == null) {
            throw new ParseException("Behavior - type not supported: " + expressionType);
        }

        return expression;
    }


    private Map<String, Expression> parseProperties(JSONArray jsonProperties) throws JSONException, ParseException {
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


    private List<Expression> parseExpressions(JSONArray jsonExpressions) throws JSONException, ParseException {
        List<Expression> expressions = new ArrayList<>();

        for (int i = 0; i < jsonExpressions.length(); i++) {
            expressions.add(parseExpression(jsonExpressions.getJSONObject(i)));
        }

        return expressions;
    }

    private Instruction parseClassDeclaration(JSONObject bodyLine) {
        return null;
    }


    private Instruction parseVariableDeclaration(JSONObject bodyLine) throws JSONException, ParseException {
        return new Declaration(
            bodyLine.getString("type"), 
            bodyLine.getString("kind"), 
            parseDeclarations(bodyLine.getJSONArray("declarations"))
        );
    }


    private List<Declarator> parseDeclarations(JSONArray jsonDeclarations) throws JSONException, ParseException {
        List<Declarator> declarations = new ArrayList<>();

        for (int i = 0; i < jsonDeclarations.length(); i++) {
            declarations.add(parseDeclarator(jsonDeclarations.getJSONObject(i)));
        }

        return declarations;
    }


    private Declarator parseDeclarator(JSONObject jsonDeclarator) throws JSONException, ParseException {
        if (jsonDeclarator.isNull("init")) {
            return new Declarator(
                jsonDeclarator.getString("type"),
                jsonDeclarator.getJSONObject("id").getString("type"),
                jsonDeclarator.getJSONObject("id").getString("name"),
                null
            );
        }

        return new Declarator(
            jsonDeclarator.getString("type"),
            jsonDeclarator.getJSONObject("id").getString("type"),
            jsonDeclarator.getJSONObject("id").getString("name"),
            parseExpression(jsonDeclarator.getJSONObject("init"))
        );
    }
}
