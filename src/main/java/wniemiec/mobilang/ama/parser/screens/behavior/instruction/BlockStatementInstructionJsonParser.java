package wniemiec.mobilang.ama.parser.screens.behavior.instruction;

import org.json.JSONException;
import org.json.JSONObject;

import wniemiec.mobilang.ama.models.behavior.BlockStatement;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.parser.screens.behavior.BlockCodeParser;


/**
 * Responsible for parsing block statements from behavior node from MobiLang 
 * AST.
 */
class BlockStatementInstructionJsonParser implements InstructionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static BlockStatementInstructionJsonParser instance;
    private final BlockCodeParser blockCodeParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private BlockStatementInstructionJsonParser() {
        blockCodeParser = BlockCodeParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static BlockStatementInstructionJsonParser getInstance() {
        if (instance == null) {
            instance = new BlockStatementInstructionJsonParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public Instruction parse(JSONObject jsonObject) 
    throws JSONException, ParseException {
        return new BlockStatement(
            blockCodeParser.parse(jsonObject.getJSONArray("body"))
        );
    }
}
