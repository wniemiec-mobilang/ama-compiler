package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;

class CallExpression extends Expression {
    Expression callee;
    List<Expression> arguments = new ArrayList<>();
    public CallExpression(Expression callee, List<Expression> arguments) {
        this.callee = callee;

        if (arguments != null)
            this.arguments = arguments;
    }

    public String toCode() {
        return callee.toCode() + "(" + arguments + ")";
    }    
}
