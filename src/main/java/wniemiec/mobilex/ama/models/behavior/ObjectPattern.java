package wniemiec.mobilex.ama.models.behavior;

import java.util.List;

/**
 * Responsible for representing an object pattern from behavior code.
 * 
 * Format: { a, b: c } = Object deconstructing pattern.
 */
public class ObjectPattern extends Pattern {
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ObjectPattern(List<AssignmentProperty> properties) {
        super(properties);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        return "{" + elementsToCode() + "}";
    }

    @Override
    public String toString() {
        return "ObjectPattern [properties=" + elementsToString() + "]";
    }
}
