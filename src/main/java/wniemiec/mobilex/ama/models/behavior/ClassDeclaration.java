package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a class declaration from behavior code. 
 */
public class ClassDeclaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression id;
    private final Expression superClass;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ClassDeclaration(Expression id, Expression superClass, Instruction body) {
        this.id = id;
        this.superClass = superClass;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("class ");
        code.append(id.toCode());
        code.append(" ");
        
        if (superClass != null) {
            code.append("extends ");
            code.append(superClass.toCode());
            code.append(" ");
        }

        code.append(body.toCode());

        return code.toString();
    }

    @Override
    public String toString() {
        return "ClassDeclaration [" 
            + "body=" + body 
            + ", id=" + id 
            + ", superClass=" + superClass 
        + "]";
    }
}
