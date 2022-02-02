package wniemiec.mobilang.asc.models;

import java.util.List;

import wniemiec.mobilang.asc.models.behavior.Instruction;

public class Behavior {
    List<Instruction> code;

    public Behavior(List<Instruction> code) {
        this.code = code;
    }

    public void print() {
        for (Instruction instruction : code) {
            System.out.println(instruction);
        }
    }

    public List<Instruction> getCode() {
        return code;
    }
}
