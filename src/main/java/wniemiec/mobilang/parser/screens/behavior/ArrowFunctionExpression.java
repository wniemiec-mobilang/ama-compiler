package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String toString() {
        return  "[ArrowFunctionExpression] {" + body.toCode() + "(" + params + ")" + "{async: " + async + "} }";
    }

    public String toCode() {
        //return  "[ArrowFunctionExpression] {" + body.toCode() + "(" + params + ")" + "{async: " + async + "} }";
        return "(" + paramsToCode() + ") => " + body.toCode();
    }        

    private String paramsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> argumentsAsCode = params.stream().map(a -> a.toCode()).collect(Collectors.toList());

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