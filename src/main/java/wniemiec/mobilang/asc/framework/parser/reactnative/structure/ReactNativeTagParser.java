package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing tags parsed from MobiLang AST, generating React 
 * Native tags.
 */
abstract class ReactNativeTagParser {

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
        parseAttributes(tag, reactNativeTag);

        return reactNativeTag;
    }

    protected abstract Tag buildReactNativeTagFrom(Tag tag);
    
    private void copyChildren(Tag tag, Tag reactNativeTag) {
        reactNativeTag.setChildren(tag.getChildren());
    }

    private void parseAttributes(Tag tag, Tag reactNativeTag) {
        if (tag.hasAttribute("id")) {
            reactNativeTag.addAttribute("id", tag.getAttribute("id"));
        }

        if (tag.hasAttribute("class")) {
            reactNativeTag.addAttribute("class", tag.getAttribute("class"));
        }
    }
}
