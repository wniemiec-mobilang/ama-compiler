package wniemiec.mobilang.asc.models.behavior;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing an array expression from behavior code.
 */
public class ArrayExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Expression> elements;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ArrayExpression(List<Expression> elements) {
        this.elements = elements;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "[" + elementsToCode() + "]";
    }

    private String elementsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> elementsAsCode = getElementsAsCode();

        for (int i = 0; i < elementsAsCode.size(); i++) {
            code.append(elementsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getElementsAsCode() {
        return elements
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ArrayExpression [elements=" + elements + "]";
    }
}
