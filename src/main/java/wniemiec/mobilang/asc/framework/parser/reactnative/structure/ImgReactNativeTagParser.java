package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing 'img' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class ImgReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static ImgReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ImgReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static ImgReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new ImgReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        Tag reactNativeTag = new Tag("Image");
        
        buildTagAttributes(tag, reactNativeTag);

        return reactNativeTag;
    }

    private void buildTagAttributes(Tag tag, Tag reactNativeTag) {
        reactNativeTag.addAttribute(
            "source", 
            "{{uri: '" + tag.getAttribute("src") + "'}}"
        );

        if (tag.hasAttribute("width")) {
            reactNativeTag.addStyle("width", tag.getAttribute("width") + "px");
        }

        if (tag.hasAttribute("height")) {
            reactNativeTag.addStyle("height", tag.getAttribute("height") + "px");
        }
    }
}
