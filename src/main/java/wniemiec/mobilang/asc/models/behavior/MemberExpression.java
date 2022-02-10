package wniemiec.mobilang.asc.models.behavior;


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
        this.propertyType = propertyType;
        this.propertyName = propertyName;
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

        code.append(object.toCode());

        if (hasPropertyName()) {
            code.append('.');
            code.append(propertyName);
        }
        else {
            code.append('[');
            code.append(value);
            code.append(']');
        }

        return code.toString();
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
}
