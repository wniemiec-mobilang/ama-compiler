package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an instruction from behavior code.
 */
public interface Instruction {

    /**
     * Generates code for instruction.
     * 
     * @return      Code
     */
    String toCode();
}