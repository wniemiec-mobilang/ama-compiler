package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import wniemiec.io.java.Consolex;


/**
 * Responsible for representing behavior code.
 */
public class Behavior {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Instruction> instructions;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Behavior() {
        this(new ArrayList<>());
    }

    public Behavior(List<Instruction> instructions) {
        this.instructions = instructions;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void print() {
        for (Instruction instruction : instructions) {
            Consolex.writeLine(instruction);
        }
    }

    public List<String> toCode() {
        List<String> code = new ArrayList<>();

        for (Instruction instruction : instructions) {
            code.addAll(extractCodeFromInstruction(instruction));
        }

        return code;
    }

    private List<String> extractCodeFromInstruction(Instruction instruction) {
        List<String> code = new ArrayList<>();

        for (String rawCode : instruction.toCode().split("\n")) {
            code.add(rawCode);
        }

        return code;
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<Instruction> getCode() {
        return instructions;
    }
}
