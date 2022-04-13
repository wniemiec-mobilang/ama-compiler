package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing an expression from behavior code.
 */
public interface Expression {

    /**
     * Generates code for expression.
     * 
     * @return      Code
     */
    String toCode();
}