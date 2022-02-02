package wniemiec.mobilang.asc.models.behavior;

import java.util.List;
import java.util.stream.Collectors;

public class ArrayExpression extends Expression {

    private List<Expression> elements;

    public ArrayExpression(List<Expression> elements) {
        this.elements = elements;
    }

    @Override
    public String toCode() {
        return "[" + elementsToCode() + "]";
    }

    @Override
    public String toString() {
        return "ArrayExpression [elements=" + elements + "]";
    }

    private String elementsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> elementsAsCode = elements.stream().map(a -> a.toCode()).collect(Collectors.toList());

        for (int i = 0; i < elementsAsCode.size(); i++) {
            sb.append(elementsAsCode.get(i));
            sb.append(',');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
        //return 
    }
}
