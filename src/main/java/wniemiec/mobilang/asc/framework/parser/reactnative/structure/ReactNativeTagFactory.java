package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import wniemiec.mobilang.asc.parser.exception.FactoryException;
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
    throws FactoryException {
        try {
            Class<?> jsonParserClass = getJsonParserClass(tagType);
            
            return invokeGetInstanceMethod(jsonParserClass);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
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

        return buildTagParserClass(normalizedType);
    }


    private static Class<?> buildTagParserClass(String tagType) 
    throws ClassNotFoundException {
        if (tagType.isBlank()) {
            return Class.forName(EXPRESSION_PACKAGE + "Empty" + CLASS_SUFFIX);
        }

        return Class.forName(EXPRESSION_PACKAGE + tagType + CLASS_SUFFIX);
    }
}
