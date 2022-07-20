package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;
import wniemiec.mobilex.ama.models.behavior.MethodDefinition;


/**
 * Responsible for parsing method definitions (part of a class body) from 
 * behavior node from MobiLang AST.
 */
class MethodDefinitionInstructionJsonParser implements InstructionJsonParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static MethodDefinitionInstructionJsonParser instance;
    private final InstructionParser instructionParser;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private MethodDefinitionInstructionJsonParser() {
        instructionParser = InstructionParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static MethodDefinitionInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new MethodDefinitionInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new MethodDefinition(
            jsonObject.getBoolean("computed"),
            jsonObject.getBoolean("static"),
            jsonObject.getString("kind"),
            expressionParser.parse(jsonObject.getJSONObject("key")),
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
