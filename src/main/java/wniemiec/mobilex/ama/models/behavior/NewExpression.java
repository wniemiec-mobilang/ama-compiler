package wniemiec.mobilex.ama.models.behavior;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing constructor calls from behavior code. A 
 * constructor call is composed of a callee and may have some arguments.
 */
public class NewExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression callee;
    private final List<Expression> arguments;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public NewExpression(Expression callee, List<Expression> arguments) {
        this.callee = callee;
        this.arguments = arguments;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("new ");
        code.append(callee.toCode());
        code.append('(');

        if (arguments != null && !arguments.isEmpty()) {
            code.append(argumentsToCode());
        }
        
        code.append(") " );

        return code.toString();
    }        

    private String argumentsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> paramsAsCode = getArgumentsAsCode();

        for (int i = 0; i < paramsAsCode.size(); i++) {
            code.append(paramsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getArgumentsAsCode() {
        return arguments
            .stream().map(Expression::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  "[NewExpression] {" 
            + callee 
            + "(" + arguments + ")" 
        + "} }";
    }
}
