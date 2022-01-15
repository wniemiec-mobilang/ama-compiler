package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TemplateLiteral extends Expression {
    List<Expression> expressions = new ArrayList<>();
    List<Expression> quasis = new ArrayList<>();
    public TemplateLiteral(List<Expression> expressions, List<Expression> quasis) {
        if (expressions != null)
            this.expressions = expressions;

        if (quasis != null)
            this.quasis = quasis;
    }

    @Override
    public String toCode() {
        return "`" + expressionsToCode() + "`";
    }

    private String expressionsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> expressionsAsCode = expressions.stream().map(a -> a.toCode()).collect(Collectors.toList());
        List<String> quasisAsCode = quasis.stream().map(a -> a.toCode()).collect(Collectors.toList());

        for (int i = 0; i < expressionsAsCode.size(); i++) {
            sb.append(quasisAsCode.get(i));
            sb.append("${");
            sb.append(expressionsAsCode.get(i));
            sb.append('}');
        }

        if ((sb.length() > 0) || (expressionsAsCode.isEmpty())) {
            sb.append(quasisAsCode.get(quasisAsCode.size()-1));
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return  "[TemplateLiteral] {" + "[TemplateLiteral:" + expressions + " | " + quasis + "] }";
    }
}