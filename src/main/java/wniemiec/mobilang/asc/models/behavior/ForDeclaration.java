package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a for declaration from behavior code.
 */
public class ForDeclaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Instruction init; 
    private final Expression test;
    private final Expression update;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ForDeclaration(
        Instruction init, 
        Expression test, 
        Expression update,
        Instruction body
    ) {
        this.init = init;
        this.test = test;
        this.update = update;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("for (");
        code.append(init.toCode());
        code.append(';');
        code.append(test.toCode());
        code.append(';');
        code.append(update.toCode());
        code.append(") ");
        code.append(body.toCode());

        return code.toString();
    }
}
