package wniemiec.mobilang.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing a call expression from behavior code.
 */
public class CallExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression callee;
    private final List<Expression> arguments;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public CallExpression(Expression callee, List<Expression> arguments) {
        this.callee = callee;
        this.arguments = (arguments == null) ? new ArrayList<>() : arguments;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return callee.toCode() + "(" + argumentsToCode() + ")";
    }

    private String argumentsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> argumentsAsCode = getArgumentsAsCode();

        for (int i = 0; i < argumentsAsCode.size(); i++) {
            code.append(argumentsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getArgumentsAsCode() {
        return arguments
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "[CallExpression] {" + callee + "(" + arguments + ") }";

    }
}
