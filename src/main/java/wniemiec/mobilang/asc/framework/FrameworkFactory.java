package wniemiec.mobilang.asc.framework;

import java.lang.reflect.InvocationTargetException;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.parser.exception.FactoryException;
import wniemiec.util.java.StringUtils;


/**
 * Provides factories of a framework.
 */
public abstract class FrameworkFactory {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String FRAMEWORK_PACKAGE;
    private static final String FRAMEWORK_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        FRAMEWORK_PACKAGE = "wniemiec.mobilang.asc.framework";
        FRAMEWORK_SUFFIX = "FrameworkFactory";
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Provides coder factory of a framework.
     * 
     * @return      Coder factory
     */
    public abstract FrameworkCoderFactory getCoderFactory();

    /**
     * Provides project manager factory of a framework.
     * 
     * @return      Project manager factory
     */
    public abstract FrameworkProjectManagerFactory getProjectManagerFactory();

    public static FrameworkFactory getInstance(String frameworkName) 
    throws FactoryException {
        try {
            Class<?> frameworkClass = getFrameworkFactoryClassByName(frameworkName);
            
            return invokeConstructor(frameworkClass);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
    }

    private static Class<?> getFrameworkFactoryClassByName(String name) 
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
        className.append(normalizedName);
        className.append(FRAMEWORK_SUFFIX);

        return Class.forName(className.toString());
    }

    private static FrameworkFactory invokeConstructor(Class<?> frameworkClass) 
    throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
    InvocationTargetException, NoSuchMethodException, SecurityException {
        return ((FrameworkFactory) frameworkClass.getConstructor().newInstance());
    }
}
