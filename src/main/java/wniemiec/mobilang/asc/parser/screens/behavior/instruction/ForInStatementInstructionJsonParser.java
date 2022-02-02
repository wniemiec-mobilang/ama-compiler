package wniemiec.mobilang.asc.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.ForInDeclaration;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing for in statements from behavior node from MobiLang 
 * AST.
 */
class ForInStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ForInStatementInstructionJsonParser instance;
    private InstructionParser instructionParser;
    private ExpressionParser expressionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ForInStatementInstructionJsonParser() {
        instructionParser = InstructionParser.getInstance();
        expressionParser = ExpressionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ForInStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new ForInStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new ForInDeclaration(
            instructionParser.parse(jsonObject.getJSONObject("left")), 
            expressionParser.parse(jsonObject.getJSONObject("right")), 
            instructionParser.parse(jsonObject.getJSONObject("body"))
        );
    }
}
