package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import wniemiec.mobilang.asc.parser.exception.ParserFactoryException;
import wniemiec.mobilang.asc.utils.StringUtils;


/**
 * Responsible for providing React Native tag parser instances.
 */
class ReactNativeTagFactory {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String EXPRESSION_PACKAGE;
    private static final String CLASS_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        EXPRESSION_PACKAGE = "wniemiec.mobilang.asc.framework.parser.reactnative.structure.";
        CLASS_SUFFIX = "ReactNativeTagParser";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ReactNativeTagFactory() {
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static ReactNativeTagParser get(String tagType) 
    throws ParserFactoryException {
        try {
            Class<?> jsonParserClass = getJsonParserClass(tagType);
            
            return invokeGetInstanceMethod(jsonParserClass);
        } 
        catch (Exception e) {
            throw new ParserFactoryException(e.getMessage());
        }
    }

    private static ReactNativeTagParser invokeGetInstanceMethod(Class<?> jsonParserClass)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method getInstance = jsonParserClass.getMethod("getInstance");
        
        return ((ReactNativeTagParser) getInstance.invoke(jsonParserClass));
    }

    private static Class<?> getJsonParserClass(String type)
    throws ClassNotFoundException {
        String normalizedType = StringUtils.capitalize(type);

        return Class.forName(EXPRESSION_PACKAGE + normalizedType + CLASS_SUFFIX);
    }
}
