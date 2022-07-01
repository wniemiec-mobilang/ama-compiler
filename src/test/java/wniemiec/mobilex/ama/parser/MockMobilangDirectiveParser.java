package wniemiec.mobilex.ama.parser;

import java.util.Map;

import wniemiec.mobilex.ama.parser.MobilangDirectiveParser;


class MockMobilangDirectiveParser extends MobilangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    @Override
    protected String swapScreenDirectiveWithParametersFor(
        String screenName, 
        Map<String, String> parameters
    ) {
        StringBuilder code = new StringBuilder();

        code.append(screenName);

        if (!parameters.isEmpty()) {
            code.append("?");
        }

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            code.append(entry.getKey());
            code.append("=");
            code.append(entry.getValue());
            code.append("&");
        }

        if (code.charAt(code.length()-1) == '&') {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    @Override
    protected String swapScreenDirectiveFor(String screenName) {
        return screenName;
    }

    @Override
    protected String replaceParamDirectiveWith(String paramName) {
        return paramName;
    }

    @Override
    protected String swapInputDirectiveFor(String inputId) {
        return inputId;
    }
}
