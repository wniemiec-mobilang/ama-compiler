package wniemiec.mobilang.asc.framework.parser.reactnative.behavior;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.util.java.StringUtils;

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
        Pattern pattern = Pattern.compile(".*window\\.location\\.href[\\s\\t]*=[\\s\\t]*([^;]+).*");
        Matcher matcher = pattern.matcher(code);

        if (!matcher.matches()) {
            return code;
        }

        String value = matcher.group(1);
        StringBuilder parsedCode = new StringBuilder();

        parsedCode.append(code.split("window.location.href")[0]);
        parsedCode.append("props.navigation.navigate(");
        parsedCode.append(buildNavigateArgs(value));
        parsedCode.append(')');

        String[] terms = code.split(value);

        if (terms.length > 1) {
            parsedCode.append(terms[1]);
        }

        return parsedCode.toString();
    }

    private String buildNavigateArgs(String windowLocationValue) {
        StringBuilder code = new StringBuilder();
        String[] terms = windowLocationValue.split("\\?");

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
}
