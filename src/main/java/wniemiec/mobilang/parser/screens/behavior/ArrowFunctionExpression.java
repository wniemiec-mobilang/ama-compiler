package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;

class ArrowFunctionExpression extends Expression {
    boolean async;
    List<Expression> params = new ArrayList<>();
    Expression body;
    public ArrowFunctionExpression(boolean async, List<Expression> params, Expression body) {
        this.async = async;

        if (params != null)
            this.params = params;

        this.body = body;
    }

    public String toCode() {
        return body.toCode() + "(" + params + ")" + "{async: " + async + "}";
    }        
}