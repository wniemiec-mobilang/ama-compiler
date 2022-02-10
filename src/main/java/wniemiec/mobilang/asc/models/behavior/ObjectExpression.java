package wniemiec.mobilang.asc.models.behavior;

import java.util.Map;


/**
 * Responsible for representing an object expression from behavior code.
 */
public class ObjectExpression extends Expression {

    private Map<String, Expression> properties;

    public ObjectExpression(Map<String, Expression> properties) {
        this.properties = properties;
    }

    @Override
    public String toCode() {
        return propertiesToCode();
    }

    @Override
    public String toString() {
        return "ObjectExpression [properties=" + properties + "]";
    }

    private String propertiesToCode() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (Map.Entry<String, Expression> prop : properties.entrySet()) {
            sb.append(prop.getKey());
            sb.append(": ");
            sb.append(prop.getValue().toCode());
            sb.append(',');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        sb.append("}");

        return sb.toString();
    }
}
