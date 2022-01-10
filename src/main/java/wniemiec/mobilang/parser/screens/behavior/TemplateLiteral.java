package wniemiec.mobilang.parser.screens.behavior;

import java.util.ArrayList;
import java.util.List;

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
        return "[TemplateLiteral:" + expressions + " | " + quasis + "]";
    }
}