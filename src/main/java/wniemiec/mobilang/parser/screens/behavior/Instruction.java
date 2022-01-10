package wniemiec.mobilang.parser.screens.behavior;

abstract class Instruction {

    String type;

    protected Instruction(String type) {
        this.type = type;
    }
}