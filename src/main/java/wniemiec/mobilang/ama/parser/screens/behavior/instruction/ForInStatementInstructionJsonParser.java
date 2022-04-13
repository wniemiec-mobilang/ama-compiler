package wniemiec.mobilang.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.ForInDeclaration;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.parser.screens.behavior.expression.ExpressionParser;


/**
 * Responsible for parsing for in statements from behavior node from MobiLang 
 * AST.
 */
class ForInStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ForInStatementInstructionJsonParser instance;
    private final InstructionParser instructionParser;
    private final ExpressionParser expressionParser;


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
