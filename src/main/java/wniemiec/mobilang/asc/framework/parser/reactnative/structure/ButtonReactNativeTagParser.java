package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing 'button' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class ButtonReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ButtonReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ButtonReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ButtonReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new ButtonReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        Tag reactNativeTag = new Tag("TouchableOpacity");

        if (tag.hasAttribute("onclick")) {
            reactNativeTag.addAttribute("onPress", tag.getAttribute("onclick"));
        }

        return reactNativeTag;
    }
}
