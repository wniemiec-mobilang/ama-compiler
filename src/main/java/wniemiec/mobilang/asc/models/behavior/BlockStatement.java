package wniemiec.mobilang.asc.models.behavior;

import java.util.List;


/**
 * Responsible for representing a block statement from behavior code.
 */
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
