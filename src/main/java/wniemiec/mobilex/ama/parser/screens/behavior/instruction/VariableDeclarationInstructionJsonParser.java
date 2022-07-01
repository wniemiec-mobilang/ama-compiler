package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing variable declarations from behavior node from 
 * MobiLang AST.
 */
class VariableDeclarationInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static VariableDeclarationInstructionJsonParser instance;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private VariableDeclarationInstructionJsonParser() {
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static VariableDeclarationInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new VariableDeclarationInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new Declaration(
            jsonObject.getString("kind"), 
            parseDeclarations(jsonObject.getJSONArray("declarations"))
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
