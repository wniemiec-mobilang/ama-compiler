package wniemiec.mobilang.ama.framework.reactnative;

import wniemiec.mobilang.ama.parser.MobiLangDirectiveParser;


class ReactNativeMobiLangDirectiveParser extends MobiLangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    @Override
    protected String replaceScreenDirectiveWith(String screenName) {
        return screenName + ".html";
    }

    @Override
    protected String replaceParamDirectiveWith(String paramName) {
        StringBuilder code = new StringBuilder();

        code.append("window.location.href.split('?')[1].split(\"");
        code.append(paramName);
        code.append("=\")[1].split(\"&\")[0]");

        return code.toString();
    }
}
