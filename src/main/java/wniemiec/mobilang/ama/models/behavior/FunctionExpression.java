package wniemiec.mobilang.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing an arrow function expression from behavior code.
 */
public class FunctionExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final boolean async;
    private final List<Expression> params;
    private final String bodyCode;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    private FunctionExpression(boolean async, List<Expression> params, String bodyCode) {
        this.async = async;
        this.params = (params == null) ? new ArrayList<>() : params;
        this.bodyCode = bodyCode;
    }
    
    public FunctionExpression(boolean async, List<Expression> params, Instruction body) {
        this(async, params, body.toCode());
    }

    public FunctionExpression(boolean async, List<Expression> params, Expression body) {
        this(async, params, body.toCode());
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(async ? "async " : "");
        code.append('(');
        code.append(paramsToCode());
        code.append(") => " );
        code.append(bodyCode);

        return code.toString();
    }        

    private String paramsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> paramsAsCode = getParamsAsCode();

        for (int i = 0; i < paramsAsCode.size(); i++) {
            code.append(paramsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getParamsAsCode() {
        return params
            .stream().map(Expression::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  "[ArrowFunctionExpression] {" 
            + bodyCode 
            + "(" + params + ")" 
            + "{async: " + async 
        + "} }";
    }
}