package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for parsing MobiLang directives.
 */
class DirectiveReactNativeParser {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public String parse(String code) {
        String parsedCode = code;

        if (isScreenDirective(code)) {
            parsedCode = parseScreenDirective(code);
        }
        
        return parsedCode;
    }

    private boolean isScreenDirective(String code) {
        return code.contains("mobilang:screen");
    }

    private String parseScreenDirective(String code) {
        String parsedCode = code;
        Pattern pattern = Pattern.compile(".+mobilang:screen:([A-z0-9]+).+");
        Matcher matcher = pattern.matcher(code);

        if (matcher.matches()) {
            String screenName = matcher.group(1);
            String normalizedScreenName = StringUtils.capitalize(screenName) + "Screen";

            parsedCode = code.replaceAll("mobilang:screen:([A-z0-9]+)", normalizedScreenName);
        }

        return parsedCode;
    }
}