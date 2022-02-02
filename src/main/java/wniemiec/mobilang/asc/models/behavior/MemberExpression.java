package wniemiec.mobilang.asc.models.behavior;

public class MemberExpression extends Expression {
    Expression object;
    String propertyType;
    String propertyName;
    int value;
    boolean computed;
    boolean optional;
   
    public MemberExpression(Expression object, String propertyType, String propertyName, int value, boolean computed,
            boolean optional) {
        this.object = object;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.value = value;
        this.computed = computed;
        this.optional = optional;
    }

    @Override
    public String toCode() {
        if (propertyName.equals("")) {
            return object.toCode() + "[" + value + "]";
        }

        return object.toCode() + "." + propertyName;
    }
    
    public String toString() {
        if (propertyName.equals("")) {
            return  "[MemberExpression] {" + object + "{type: " + propertyType + "; value: " + value + "; computed: " + computed + "; optional: " + optional + "} }";
        }

        return  "[MemberExpression] {" + object + "{type: " + propertyType + "; name: " + propertyName + "; computed: " + computed + "; optional: " + optional + "} }";
        
    }
}
