package wniemiec.mobilex.ama.framework.reactnative.parser;

import java.util.Map;

import wniemiec.mobilex.ama.parser.MobilangDirectiveParser;


public class ReactNativeMobilangDirectiveParser extends MobilangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    @Override
    protected String swapScreenDirectiveWithParametersFor(String screenName, Map<String, String> parameters) {
        StringBuilder code = new StringBuilder();

        code.append(swapScreenDirectiveFor(screenName));
        code.append('?');
        
        parameters.forEach((key, value) -> {
            code.append(key);
            code.append('=');
            code.append(value);
            code.append('&');
        });

        code.deleteCharAt(code.length()-1); // Removes last '&'

        return code.toString();
    }

    @Override
    protected String swapScreenDirectiveFor(String screenName) {
        return screenName + ".html";
    }

    @Override
    protected String replaceParamDirectiveWith(String paramName) {
        StringBuilder code = new StringBuilder();

        code.append("window.location.href.split('?')[1].split('");
        code.append(paramName);
        code.append("=')[1].split('&')[0]");

        return code.toString();
    }

    @Override
    protected String swapInputDirectiveFor(String inputId) {
        StringBuilder code = new StringBuilder();

        code.append("document.getElementById('");
        code.append(inputId);
        code.append("')");

        return code.toString();
    }
}
