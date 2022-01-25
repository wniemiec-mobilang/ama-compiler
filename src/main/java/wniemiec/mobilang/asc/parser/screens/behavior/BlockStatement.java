package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.List;

public class BlockStatement extends Instruction {

    private List<Instruction> body;

    public BlockStatement(List<Instruction> body) {
        super("BlockStatement");
        this.body = body;
    }

    @Override
    public String toCode() {
        return "{" + bodyToCode() + "}";
    }

    private String bodyToCode() {
        StringBuilder sb = new StringBuilder();

        sb.append('\n');

        for (Instruction line : body) {
            sb.append(line.toCode());
            sb.append('\n');
        }

        return sb.toString();
    }
}
