package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a catch clause from a try statement.
 * 
 * Format: catch (param) body
 * Note: Must be part of a TryStatement.
 */
public class CatchClause implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Expression param;
    private final BlockStatement body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public CatchClause(Expression param, BlockStatement body) {
        this.param = param;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("catch (");
        code.append(param.toCode());
        code.append(") ");
        code.append(body.toCode());

        return code.toString();
    }
}
