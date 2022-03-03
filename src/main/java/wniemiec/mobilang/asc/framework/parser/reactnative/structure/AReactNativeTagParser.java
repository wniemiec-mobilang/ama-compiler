package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing 'a' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class AReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static AReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private AReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static AReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new AReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        Tag reactNativeTag = new Tag("TouchableOpacity");
        
        buildTagAttributes(tag, reactNativeTag);
        buildTagContent(tag, reactNativeTag);

        return reactNativeTag;
    }

    private void buildTagAttributes(Tag tag, Tag reactNativeTag) {
        reactNativeTag.addAttribute(
            "onPress", 
            "{() => props.route.params.query = '" + tag.getAttribute("href") + "'}"
        );
    }

    private void buildTagContent(Tag tag, Tag reactNativeTag) {
        Tag textTag = new Tag("Text");
        
        textTag.setValue(tag.getValue());
        reactNativeTag.addChild(textTag);

        parseStyleAttribute("text-decoration", tag, textTag);
        parseStyleAttribute("text-align", tag, textTag);
        parseStyleAttribute("color", tag, textTag);
        parseStyleAttribute("font-align", tag, textTag);
        parseStyleAttribute("font-weight", tag, textTag);
        parseStyleAttribute("font-size", tag, textTag);
        parseStyleAttribute("font-family", tag, textTag);
    }

    private void parseStyleAttribute(String attribute, Tag tag, Tag rnTag) {
        if (!tag.hasStyle(attribute)) {
            return;
        }
        
        rnTag.addStyle(attribute, tag.getStyle(attribute));
        tag.removeStyle(attribute);
    }
}
