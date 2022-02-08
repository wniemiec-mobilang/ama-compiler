package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;


/**
 * Responsible for parsing window class code from behavior code on React Native 
 * framework.
 */
class WindowReactNativeParser {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public String parse(String code) {
        String parsedCode = code;

        if (isWindowLocationHref(code)) {
            parsedCode = parseWindowLocationHref(code);
        }
        
        return parsedCode;
    }

    private boolean isWindowLocationHref(String code) {
        return code.contains("window.location.href");
    }

    private String parseWindowLocationHref(String code) {
        return code.replace("window.location.href", "props.route.params.query");
    }
}
