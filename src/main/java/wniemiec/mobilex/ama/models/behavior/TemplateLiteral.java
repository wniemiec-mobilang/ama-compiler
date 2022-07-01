package wniemiec.mobilex.ama.models.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing a template literal from behavior code.
 */
public class TemplateLiteral implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Expression> expressions;
    private final List<Expression> quasis;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public TemplateLiteral(List<Expression> expressions, List<Expression> quasis) {
        this.expressions = (expressions == null) ? new ArrayList<>() : expressions;
        this.quasis = (quasis == null) ? new ArrayList<>() : quasis;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "`" + expressionsToCode() + "`";
    }

    private String expressionsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> expressionsAsCode = getExpressionCode();
        List<String> quasisAsCode = getQuasisCode();

        for (int i = 0; i < expressionsAsCode.size(); i++) {
            if (!quasisAsCode.isEmpty()) {
                code.append(quasisAsCode.get(i));
            }

            code.append("${");
            code.append(expressionsAsCode.get(i));
            code.append('}');
        }

        if (expressionsAsCode.isEmpty() && !quasisAsCode.isEmpty()) {
            code.append(quasisAsCode.get(quasisAsCode.size()-1));
        }

        return code.toString();
    }

    private List<String> getExpressionCode() {
        return expressions
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }

    private List<String> getQuasisCode() {
        return quasis
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  "[TemplateLiteral] {" 
            + "[TemplateLiteral:" + expressions + " | " + quasis + "] " 
        + "}";
    }
}