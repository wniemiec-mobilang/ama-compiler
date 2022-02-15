package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing tags parsed from MobiLang AST, generating React 
 * Native tags.
 */
abstract class ReactNativeTagParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String ID_LABEL;
    private static final String CLASS_LABEL;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        ID_LABEL = "id";
        CLASS_LABEL = "class";
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    /**
     * Parses a tag, generating a React Native tag.
     * 
     * @param       tag Tag to be parsed
     * 
     * @return      React native tag
     */
    public final Tag parse(Tag tag) {
        Tag reactNativeTag = buildReactNativeTagFrom(tag);

        copyChildren(tag, reactNativeTag);
        copyStyle(tag, reactNativeTag);
        parseAttributes(tag, reactNativeTag);

        return reactNativeTag;
    }

    protected abstract Tag buildReactNativeTagFrom(Tag tag);
    
    private void copyChildren(Tag tag, Tag reactNativeTag) {
        reactNativeTag.mergeChildren(tag.getChildren());
    }

    private void copyStyle(Tag tag, Tag reactNativeTag) {
        reactNativeTag.setStyle(tag.getStyle());
    }

    private void parseAttributes(Tag tag, Tag reactNativeTag) {
        if (tag.hasAttribute(ID_LABEL)) {
            reactNativeTag.addAttribute(ID_LABEL, tag.getAttribute(ID_LABEL));
        }

        if (tag.hasAttribute(CLASS_LABEL)) {
            reactNativeTag.addAttribute(CLASS_LABEL, tag.getAttribute(CLASS_LABEL));
        }
    }
}
