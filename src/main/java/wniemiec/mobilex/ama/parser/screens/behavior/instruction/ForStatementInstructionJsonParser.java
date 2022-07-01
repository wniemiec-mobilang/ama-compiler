package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilex.ama.models.behavior.ForDeclaration;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing for statements from behavior node from MobiLang AST.
 */
class ForStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ForStatementInstructionJsonParser instance;
    private final InstructionParser instructionParser;
    private final ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ForStatementInstructionJsonParser() {
        instructionParser = InstructionParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ForStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ForStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ForDeclaration(
            instructionParser.parse(jsonObject.getJSONObject("init")), 
            expressionParser.parse(jsonObject.getJSONObject("test")), 
            expressionParser.parse(jsonObject.getJSONObject("update")), 
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
