package wniemiec.mobilex.ama.models.behavior;

import java.util.List;
import java.util.stream.Collectors;


/**
 * A pattern can go in a parameter list or on the left side of an assignment.
 */
public abstract class Pattern implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<? extends Expression> elements;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    protected Pattern(List<? extends Expression> elements) {
        this.elements = elements;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    protected String elementsToCode() {
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

    protected List<String> getElementsAsCode() {
        return elements
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }

    protected String elementsToString() {
        return elements.toString();
    }
}
