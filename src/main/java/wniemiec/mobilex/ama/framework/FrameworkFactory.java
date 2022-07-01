package wniemiec.mobilex.ama.framework;

import java.lang.reflect.InvocationTargetException;

import wniemiec.mobilex.ama.parser.exception.FactoryException;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for providing frameworks.
 */
public class FrameworkFactory {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String FRAMEWORK_PACKAGE;
    private static final String FRAMEWORK_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        FRAMEWORK_PACKAGE = "wniemiec.mobilang.ama.framework";
        FRAMEWORK_SUFFIX = "Framework";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private FrameworkFactory() {
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static Framework getInstance(String frameworkName) 
    throws FactoryException {
        try {
            Class<?> frameworkClass = getFrameworkClassByName(frameworkName);
            
            return invokeConstructor(frameworkClass);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
    }

    private static Class<?> getFrameworkClassByName(String name) 
    throws ClassNotFoundException {
        String normalizedName = normalizeFrameworkName(name);

        return buildFrameworkClass(normalizedName);
    }

    private static String normalizeFrameworkName(String name) {
        StringBuilder normalizedName = new StringBuilder();

        for (String term : name.replace("_", "-").split("-")) {
            normalizedName.append(StringUtils.capitalize(term));
        }

        return normalizedName.toString();
    }

    private static Class<?> buildFrameworkClass(String normalizedName) 
    throws ClassNotFoundException {
        StringBuilder className = new StringBuilder();

        className.append(FRAMEWORK_PACKAGE);
        className.append('.');
        className.append(normalizedName.toLowerCase());
        className.append('.');
        className.append(normalizedName);
        className.append(FRAMEWORK_SUFFIX);

        return Class.forName(className.toString());
    }

    private static Framework invokeConstructor(Class<?> frameworkClass) 
    throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
    InvocationTargetException, NoSuchMethodException, SecurityException {
        return ((Framework) frameworkClass.getConstructor().newInstance());
    }
}
