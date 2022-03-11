package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.util.Map;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing 'button' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class InputReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static InputReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private InputReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static InputReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new InputReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        Tag reactNativeTag = new Tag("TextInput");

        buildTagAttributes(tag, reactNativeTag);
        buildTagStyle(tag, reactNativeTag);

        return reactNativeTag;
    }

    private void buildTagAttributes(Tag tag, Tag reactNativeTag) {
        for (Map.Entry<String, String> entry : tag.getAttributes().entrySet()) {
            reactNativeTag.addAttribute(entry.getKey(), entry.getValue());
        }
    }

    private void buildTagStyle(Tag tag, Tag reactNativeTag) {
        reactNativeTag.addAttribute("background-color", "white");
    }
}
