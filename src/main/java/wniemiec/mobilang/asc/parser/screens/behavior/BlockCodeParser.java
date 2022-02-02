package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.instruction.InstructionParser;


/**
 * Responsible for parsing block codes from behavior node from MobiLang AST.
 */
public class BlockCodeParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static BlockCodeParser instance;
    private InstructionParser instructionParser;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private BlockCodeParser() {
        instructionParser = InstructionParser.getInstance();
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static BlockCodeParser getInstance() {
        if (instance == null) {
            instance = new BlockCodeParser();
        }

        return instance;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<Instruction> parse(JSONArray blockCode) throws ParseException {
        List<Instruction> instructions = new ArrayList<>();

        for (int i = 0; i < blockCode.length(); i++) {
            JSONObject jsonInstruction = blockCode.getJSONObject(i);
            Instruction instruction = instructionParser.parse(jsonInstruction);

            instructions.add(instruction);
        }

        return instructions;
    }   
}
