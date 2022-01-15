package wniemiec.mobilang.asc.parser.screens.behavior;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
