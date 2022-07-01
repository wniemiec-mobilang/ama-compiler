package wniemiec.mobilex.ama.models.behavior;

/**
 * Responsible for representing a conditional expression from behavior code.
 */
public class ConditionalExpression implements Expression {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression test;
    private final Expression consequent;
    private final Expression alternate;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ConditionalExpression(
        Expression test,
        Expression consequent,
        Expression alternate
    ) {
        this.test = test;
        this.consequent = consequent;
        this.alternate = alternate;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(test.toCode());
        code.append(" ? ");
        code.append(consequent.toCode());
        code.append(" : ");
        code.append(alternate.toCode());

        return code.toString();
    }
}