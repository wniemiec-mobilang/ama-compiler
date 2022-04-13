package wniemiec.mobilang.ama.parser.screens.behavior.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import wniemiec.mobilang.ama.parser.exception.FactoryException;


/**
 * Responsible for providing ExpressionJsonParser instances.
 */
class ExpressionJsonParserFactory {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String EXPRESSION_PACKAGE;
    private static final String CLASS_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        EXPRESSION_PACKAGE = "wniemiec.mobilang.ama.parser.screens.behavior.expression.";
        CLASS_SUFFIX = "ExpressionJsonParser";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ExpressionJsonParserFactory() {
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static ExpressionJsonParser get(String expressionType) 
    throws FactoryException {
        try {
            Class<?> jsonParserClass = getExpressionJsonParserClass(expressionType);
            
            return invokeGetInstanceMethod(jsonParserClass);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
    }

    private static ExpressionJsonParser invokeGetInstanceMethod(Class<?> jsonParserClass)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method getInstance = jsonParserClass.getMethod("getInstance");
        
        return ((ExpressionJsonParser) getInstance.invoke(jsonParserClass));
    }

    private static Class<?> getExpressionJsonParserClass(String expressionType) 
    throws ClassNotFoundException {
        String expressionName = expressionType.replace("Expression", "");

        return Class.forName(EXPRESSION_PACKAGE + expressionName + CLASS_SUFFIX);
    }
}
