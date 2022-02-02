package wniemiec.mobilang.asc.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.ForDeclaration;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing for statements from behavior node from MobiLang AST.
 */
class ForStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ForStatementInstructionJsonParser instance;
    private InstructionParser instructionParser;
    private ExpressionParser expressionParser;


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
