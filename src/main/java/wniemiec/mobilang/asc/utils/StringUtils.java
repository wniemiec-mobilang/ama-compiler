package wniemiec.mobilang.asc.utils;

/**
 * Responsible for providing string utilities.
 */
public class StringUtils {
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private StringUtils() {
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static String capitalize(String str) {
        char firstChar = Character.toUpperCase(str.charAt(0));
        
        return firstChar + str.substring(1).toLowerCase();
    }
}
