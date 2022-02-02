package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import wniemiec.mobilang.asc.models.behavior.Instruction;
import wniemiec.mobilang.asc.parser.exception.ParseException;


class BlockCodeParser {

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
            Instruction instruction = instructionParser.parse(blockCode.getJSONObject(i));
            instructions.add(instruction);
        }

        return instructions;
    }   
}
