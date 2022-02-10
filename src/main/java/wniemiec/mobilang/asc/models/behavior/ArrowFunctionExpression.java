package wniemiec.mobilang.asc.models.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing an arrow function expression from behavior code.
 */
public class ArrowFunctionExpression extends Expression {
    boolean async;
    List<Expression> params = new ArrayList<>();
    String bodyCode;

    private ArrowFunctionExpression(boolean async, List<Expression> params, String bodyCode) {
        this.async = async;

        if (params != null) {
            this.params = params;
        }

        this.bodyCode = bodyCode;
    }
    
    public ArrowFunctionExpression(boolean async, List<Expression> params, Instruction body) {
        this(async, params, body.toCode());
    }

    public ArrowFunctionExpression(boolean async, List<Expression> params, Expression body) {
        this(async, params, body.toCode());
    }

    public String toString() {
        return  "[ArrowFunctionExpression] {" + bodyCode + "(" + params + ")" + "{async: " + async + "} }";
    }

    public String toCode() {
        String asyncField = async ? "async " : "";
        //return  "[ArrowFunctionExpression] {" + body.toCode() + "(" + params + ")" + "{async: " + async + "} }";
        return asyncField + "(" + paramsToCode() + ") => " + bodyCode;
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