package wniemiec.mobilang.asc.models.behavior;

import java.util.List;


/**
 * Responsible for representing behavior code.
 */
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
