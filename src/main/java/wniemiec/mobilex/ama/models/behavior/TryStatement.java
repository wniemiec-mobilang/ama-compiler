package wniemiec.mobilex.ama.models.behavior;


/**
 * Responsible for representing a try statement from behavior code.
 * 
 * Format: try { block } catch (handler.param) { handler.body } finally { finalizer }
 * Note: At least one of handler or finalizer must be non-null.
 */
public class TryStatement implements Instruction {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final BlockStatement block;
    private final CatchClause handler;
    private final BlockStatement finalizer;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public TryStatement(
        BlockStatement block, 
        CatchClause handler, 
        BlockStatement finalizer
    ) {
        this.block = block;
        this.handler = handler;
        this.finalizer = finalizer;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append("try ");
        code.append(block.toCode());

        if (handler != null) {
            code.append(' ');
            code.append(handler.toCode());
        }
        
        if (finalizer != null) {
            code.append(" finally ");
            code.append(finalizer.toCode());
        }

        return code.toString();
    }
}
