package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import wniemiec.mobilang.asc.models.Tag;


/**
 * Responsible for parsing 'div' tags parsed from MobiLang AST, generating a 
 * React Native tag.
 */
class DivReactNativeTagParser extends ReactNativeTagParser {
 
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static DivReactNativeTagParser instance;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private DivReactNativeTagParser() {
    }


    //-------------------------------------------------------------------------
    //		Factory
    //-------------------------------------------------------------------------
    public static DivReactNativeTagParser getInstance() {
        if (instance == null) {
            instance = new DivReactNativeTagParser();
        }

        return instance;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    protected Tag buildReactNativeTagFrom(Tag tag) {
        return new Tag("View");
    }
}
