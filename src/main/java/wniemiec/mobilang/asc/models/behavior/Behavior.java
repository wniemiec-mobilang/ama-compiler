package wniemiec.mobilang.asc.models.behavior;

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
    private final List<Instruction> code;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public Behavior() {
        this(new ArrayList<>());
    }

    public Behavior(List<Instruction> code) {
        this.code = code;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void print() {
        for (Instruction instruction : code) {
            Consolex.writeLine(instruction);
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<Instruction> getCode() {
        return code;
    }
}
