package wniemiec.mobilang.ama.models.behavior;

import java.util.Map;


/**
 * Responsible for representing an object expression from behavior code.
 */
public class ObjectExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Map<String, Expression> properties;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ObjectExpression(Map<String, Expression> properties) {
        this.properties = properties;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("{");
        
        for (Map.Entry<String, Expression> prop : properties.entrySet()) {
            code.append(prop.getKey());
            code.append(": ");
            code.append(prop.getValue().toCode());
            code.append(',');
        }

        if (code.length() > 1) {
            code.deleteCharAt(code.length()-1);
        }

        code.append("}");

        return code.toString();
    }

    @Override
    public String toString() {
        return "ObjectExpression [properties=" + properties + "]";
    }
}
