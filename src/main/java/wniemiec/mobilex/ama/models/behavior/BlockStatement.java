package wniemiec.mobilex.ama.models.behavior;

import java.util.Arrays;
import java.util.List;


/**
 * Responsible for representing a block statement from behavior code.
 */
public class BlockStatement implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Instruction> body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public BlockStatement(Instruction... body) {
        this(Arrays.asList(body));
    }

    public BlockStatement(List<Instruction> body) {
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "{" + bodyToCode() + "}";
    }

    private String bodyToCode() {
        StringBuilder code = new StringBuilder();

        code.append('\n');

        for (Instruction line : body) {
            code.append(line.toCode());
            code.append(';');
            code.append('\n');
        }

        return code.toString();
    }
}
