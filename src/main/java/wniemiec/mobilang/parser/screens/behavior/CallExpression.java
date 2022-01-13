package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CallExpression extends Expression {
    Expression callee;
    List<Expression> arguments = new ArrayList<>();
    public CallExpression(Expression callee, List<Expression> arguments) {
        this.callee = callee;

        if (arguments != null)
            this.arguments = arguments;
    }

    public String toString() {
        return "[CallExpression] {" + callee + "(" + arguments + ") }";

    }

    public String toCode() {
        return callee.toCode() + "(" + argumentsToCode() + ")";
    }

    private String argumentsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> argumentsAsCode = arguments.stream().map(a -> a.toCode()).collect(Collectors.toList());

        for (int i = 0; i < argumentsAsCode.size(); i++) {
            sb.append(argumentsAsCode.get(i));
            sb.append(',');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
        //return 
    }
}
