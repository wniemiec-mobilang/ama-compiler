package wniemiec.mobilang.parser.screens.behavior;

class MemberExpression extends Expression {
    Expression object;
    String propertyType;
    String propertyName;
    boolean computed;
    boolean optional;
   
    public MemberExpression(Expression object, String propertyType, String propertyName, boolean computed,
            boolean optional) {
        this.object = object;
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.computed = computed;
        this.optional = optional;
    }

    @Override
    public String toCode() {
        return object.toCode() + "{type: " + propertyType + "; name: " + propertyName + "; computed: " + computed + "; optional: " + optional + "}";
    }
}
