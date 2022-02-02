package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.BlockStatement;
import wniemiec.mobilang.asc.models.behavior.Declaration;
import wniemiec.mobilang.asc.models.behavior.Declarator;
import wniemiec.mobilang.asc.models.behavior.ExpressionStatement;
import wniemiec.mobilang.asc.models.behavior.ForDeclaration;
import wniemiec.mobilang.asc.models.behavior.ForInDeclaration;
import wniemiec.mobilang.asc.models.behavior.ForOfDeclaration;
import wniemiec.mobilang.asc.models.behavior.FunctionDeclaration;
import wniemiec.mobilang.asc.models.behavior.IfStatement;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing instructions from behavior node from MobiLang AST.
 */
public class InstructionParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static InstructionParser instance;
    private BlockCodeParser blockCodeParser;
    private ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private InstructionParser() {
        blockCodeParser = BlockCodeParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static InstructionParser getInstance() {
        if (instance == null) {
            instance = new InstructionParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Instruction parse(JSONObject bodyLine) 
    throws JSONException, ParseException {
        Instruction instruction = null;
        String type = bodyLine.getString("type");

        if (type.equals("ExpressionStatement")) {
            instruction = parseStatement(bodyLine);
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
            instruction = new BlockStatement(blockCodeParser.parse(bodyLine.getJSONArray("body")));
        }

        if (instruction == null) {
            throw new ParseException("Behavior - type not supported: " + type);
        }

        return instruction;
    }

    private Instruction parseIfStatementDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new IfStatement(
            expressionParser.parse(bodyLine.getJSONObject("test")), 
            parse(bodyLine.getJSONObject("consequent"))
        );
    }


    private Instruction parseForStatementDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new ForDeclaration(
            parse(bodyLine.getJSONObject("init")), 
            expressionParser.parse(bodyLine.getJSONObject("test")), 
            expressionParser.parse(bodyLine.getJSONObject("update")), 
            parse(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseForOfStatementDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new ForOfDeclaration(
            parse(bodyLine.getJSONObject("left")), 
            expressionParser.parse(bodyLine.getJSONObject("right")), 
            parse(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseForInStatementDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new ForInDeclaration(
            parse(bodyLine.getJSONObject("left")), 
            expressionParser.parse(bodyLine.getJSONObject("right")), 
            parse(bodyLine.getJSONObject("body"))
        );
    }

    private Instruction parseFunctionDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new FunctionDeclaration(
            bodyLine.getJSONObject("id").getString("name"), 
            bodyLine.getBoolean("async"),
            expressionParser.parse(bodyLine.getJSONArray("params")),
            parse(bodyLine.getJSONObject("body"))
        );
    }


    private Instruction parseStatement(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new ExpressionStatement(
            expressionParser.parse(bodyLine.getJSONObject("expression"))
        );
    }

    private Instruction parseClassDeclaration(JSONObject bodyLine) {
        return null;
    }


    private Instruction parseVariableDeclaration(JSONObject bodyLine) 
    throws JSONException, ParseException {
        return new Declaration(
            bodyLine.getString("type"), 
            bodyLine.getString("kind"), 
            parseDeclarations(bodyLine.getJSONArray("declarations"))
        );
    }


    private List<Declarator> parseDeclarations(JSONArray jsonDeclarations) 
    throws JSONException, ParseException {
        List<Declarator> declarations = new ArrayList<>();

        for (int i = 0; i < jsonDeclarations.length(); i++) {
            declarations.add(parseDeclarator(jsonDeclarations.getJSONObject(i)));
        }

        return declarations;
    }


    private Declarator parseDeclarator(JSONObject jsonDeclarator) 
    throws JSONException, ParseException {
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
            expressionParser.parse(jsonDeclarator.getJSONObject("init"))
        );
    }
}
