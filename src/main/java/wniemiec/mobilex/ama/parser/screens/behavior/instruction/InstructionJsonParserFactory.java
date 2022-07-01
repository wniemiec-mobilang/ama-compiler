package wniemiec.mobilex.ama.parser.screens.behavior.instruction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import wniemiec.mobilex.ama.parser.exception.FactoryException;


/**
 * Responsible for providing InstructionJsonParser instances.
 */
public class InstructionJsonParserFactory {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String EXPRESSION_PACKAGE;
    private static final String CLASS_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        EXPRESSION_PACKAGE = "wniemiec.mobilang.ama.parser.screens.behavior.instruction.";
        CLASS_SUFFIX = "InstructionJsonParser";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private InstructionJsonParserFactory() {
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static InstructionJsonParser get(String instructionType) 
    throws FactoryException {
        try {
            Class<?> jsonParserClass = getExpressionJsonParserClass(instructionType);
            
            return invokeGetInstanceMethod(jsonParserClass);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
    }

    private static InstructionJsonParser invokeGetInstanceMethod(Class<?> jsonParserClass)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method getInstance = jsonParserClass.getMethod("getInstance");
        
        return ((InstructionJsonParser) getInstance.invoke(jsonParserClass));
    }

    private static Class<?> getExpressionJsonParserClass(String expressionType) 
    throws ClassNotFoundException {
        String expressionName = expressionType.replace("Instruction", "");

        return Class.forName(EXPRESSION_PACKAGE + expressionName + CLASS_SUFFIX);
    }
}
