package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing an instruction from behavior code.
 */
public abstract class Instruction {

    String type;

    protected Instruction(String type) {
        this.type = type;
    }

    public abstract String toCode();
}