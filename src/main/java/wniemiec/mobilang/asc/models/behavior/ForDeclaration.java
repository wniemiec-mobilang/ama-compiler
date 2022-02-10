package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing a for declaration from behavior code.
 */
public class ForDeclaration extends Instruction {

    private Instruction init; 
    private Expression test;
    private Expression update;
    private Instruction body;

    public ForDeclaration(
        Instruction init, 
        Expression test, 
        Expression update,
        Instruction body
    ) {
        super("ForDeclaration");
        this.init = init;
        this.test = test;
        this.update = update;
        this.body = body;
    }

    @Override
    public String toCode() {
        return "for (" + init.toCode() + ";" + test.toCode() + ";" + update.toCode() + ") " + body.toCode();
    }


}
