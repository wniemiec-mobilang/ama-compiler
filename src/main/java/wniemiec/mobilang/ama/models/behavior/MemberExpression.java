package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing a member expression from behavior code.
 */
public class MemberExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression object;
    private final String propertyType;
    private final String propertyName;
    private final int value;
    private final boolean computed;
    private final boolean optional;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MemberExpression(
        Expression object, 
        String propertyType, 
        String propertyName, 
        int value, 
        boolean computed,
        boolean optional
    ) {
        this.object = object;
        this.propertyType = (propertyType == null) ? "" : propertyType;
        this.propertyName = (propertyName == null) ? "" : propertyName;
        this.value = value;
        this.computed = computed;
        this.optional = optional;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        putObjectIdentifier(code);
        putIndex(code);
        
        return code.toString();
    }

    private void putObjectIdentifier(StringBuilder code) {
        code.append(object.toCode());
    }

    private void putIndex(StringBuilder code) {
        if (hasPropertyName()) {
            putIndexKey(code);
        }
        else {
            putIndexValue(code);
        }
    }

    private void putIndexKey(StringBuilder code) {
        if (computed) {
            code.append('[');
            code.append(propertyName);
            code.append(']');
        }
        else {
            code.append('.');
            code.append(propertyName);
        }
    }

    private void putIndexValue(StringBuilder code) {
        code.append('[');
        code.append(value);
        code.append(']');
    }

    private boolean hasPropertyName() {
        return !propertyName.equals("");
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[MemberExpression] {");
        sb.append(object);
        sb.append("{type: ");
        sb.append(propertyType);

        if (hasPropertyName()) {
            sb.append("; name: ");
            sb.append(propertyName);
        }
        else {
            sb.append("; value: ");
            sb.append(value);
        }
        
        sb.append("; computed: ");
        sb.append(computed);
        sb.append("; optional: ");
        sb.append(optional);
        sb.append("} }");

        return sb.toString();
    }

    public boolean isOptional() {
        return optional;
    }
}
