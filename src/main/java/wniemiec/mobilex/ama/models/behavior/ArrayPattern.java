package wniemiec.mobilex.ama.models.behavior;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing an array pattern from behavior code.
 * 
 * Example: [ a, b ] = .... Array deconstructing pattern
 */
public class ArrayPattern implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Expression> elements;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ArrayPattern(List<Expression> elements) {
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
        return "ArrayPattern [elements=" + elements + "]";
    }
}
