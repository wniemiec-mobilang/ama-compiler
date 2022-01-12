package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.Parser;

public class BehaviorParser  {

    private String contentNode;

    public BehaviorParser(SortedMap<String, List<Node>> tree, Node behaviorNode) {
        contentNode = tree.get(behaviorNode.getId()).get(0).getLabel();
    }

    
    public Behavior parse() throws Exception {
        //System.out.println("-----< BEHAVIOR PARSER >-----");
        //System.out.println(contentNode);
        //System.out.println("-------------------------------\n");

        List<Instruction> code = parseJson(contentNode);
        Behavior behavior = new Behavior(code);

        //behavior.print();

        return behavior;
    }

    private List<Instruction> parseJson(String json) throws Exception {
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
            expression = new MemberExpression(
                parseExpression(jsonObject.getJSONObject("object")),
                jsonObject.getJSONObject("property").getString("type"),
                jsonObject.getJSONObject("property").optString("name", ""),
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
            expression = new Literal(jsonObject.getString("value"));
        }

        return expression;
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
