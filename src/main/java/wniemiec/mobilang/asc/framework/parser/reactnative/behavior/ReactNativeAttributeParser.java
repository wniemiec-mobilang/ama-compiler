package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.util.HashMap;
import java.util.Map;


/**
 * Responsible for parsing a tag attribute, converting it to a React Native
 * property.
 */
class ReactNativeAttributeParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Map<String, String> mapping;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        mapping = new HashMap<>();

        setUpMapping();
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ReactNativeAttributeParser() {
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private static void setUpMapping() {
        mapping.put("onclick", "OnPress");
    }

    public static String parse(String name) {
        if (!mapping.containsKey(name.toLowerCase())) {
            return name;
        }

        return mapping.get(name.toLowerCase());
    }
}
