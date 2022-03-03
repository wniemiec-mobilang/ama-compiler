package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Responsible for parsing 'body' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class BodyReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static BodyReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private BodyReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static BodyReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new BodyReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        Tag rnTag = new Tag("SafeAreaView");

        rnTag.addStyle("flex", "1");

        return rnTag;
    }
}
