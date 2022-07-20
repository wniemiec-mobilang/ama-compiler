package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a method definition from behavior code. A 
 * method definition is part of a class body.
 */
public class MethodDefinition implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private boolean computed;
    private boolean isStatic;
    private String kind;
    private Expression key;
    private Instruction value;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MethodDefinition(
        boolean computed, 
        boolean isStatic, 
        String kind, 
        Expression key, 
        Instruction body
    ) {
        this.computed = computed;
        this.isStatic = isStatic;
        this.kind = kind;
        this.key = key;
        this.value = body;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        if (isStatic) {
            code.append("static ");
        }

        code.append(key.toCode());
        code.append(value.toCode());

        return code.toString();
    }

    @Override
    public String toString() {
        return "MethodDefinition [" 
            + "computed=" + computed 
            + ", isStatic=" + isStatic 
            + ", kind=" + kind 
            + ", key=" + key 
            + ", value=" + value 
        + "]";
    }
}
