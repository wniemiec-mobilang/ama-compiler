package wniemiec.mobilex.ama.parser.screens.behavior.expression;


/**
 * Responsible for parsing arrow functions from behavior node from MobiLang AST.
 */
class ArrowFunctionExpressionJsonParser extends FunctionExpressionJsonParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ArrowFunctionExpressionJsonParser instance;


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ArrowFunctionExpressionJsonParser getInstance() {
        if (instance == null) {
            instance = new ArrowFunctionExpressionJsonParser();
        }

        return instance;
    }
}
