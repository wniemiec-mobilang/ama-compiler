package wniemiec.mobilang.asc.framework.parser.reactnative.structure;

import java.util.Arrays;

import wniemiec.mobilang.asc.models.tag.Tag;
import wniemiec.util.java.StringUtils;


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
            buildOnPressCode(tag)
        );
    }

    private String buildOnPressCode(Tag tag) {
        StringBuilder code = new StringBuilder();

        code.append("{() => props.navigation.navigate('");
        code.append(buildNavigateArgs(tag));
        code.append("')}");

        return code.toString();
    }

    private String buildNavigateArgs(Tag tag) {
        StringBuilder code = new StringBuilder();
        String[] terms = tag.getAttribute("href").split("\\?");

        code.append(terms[0]);
        
        if (terms.length > 1) {
            String args = StringUtils.implode(Arrays.asList(terms[1].split("&")), ",");

            code.append(',');
            code.append('{');
            code.append(args.replace("=", ":"));
            code.append('}');
        }

        return code.toString();
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
        //parseStyleAttribute("font-family", tag, textTag);
    }

    private void parseStyleAttribute(String attribute, Tag tag, Tag rnTag) {
        if (!tag.hasStyle(attribute)) {
            return;
        }
        
        rnTag.addStyle(attribute, tag.getStyle(attribute));
        tag.removeStyle(attribute);
    }
}
