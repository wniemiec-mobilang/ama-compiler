package wniemiec.mobilang.parser.screens.behavior;

public abstract class Instruction {

    String type;

    protected Instruction(String type) {
        this.type = type;
    }

    public abstract String toCode();
}