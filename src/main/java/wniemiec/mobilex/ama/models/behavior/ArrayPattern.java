package wniemiec.mobilex.ama.models.behavior;

import java.util.List;


/**
 * Responsible for representing an array pattern from behavior code.
 * 
 * Example: [ a, b ] = .... Array deconstructing pattern
 */
public class ArrayPattern extends Pattern {
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ArrayPattern(List<Expression> elements) {
        super(elements);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "[" + elementsToCode() + "]";
    }

    @Override
    public String toString() {
        return "ArrayPattern [elements=" + elementsToString() + "]";
    }
}
