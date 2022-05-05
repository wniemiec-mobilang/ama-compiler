package wniemiec.mobilang.ama.framework.ionic;

import wniemiec.mobilang.ama.parser.MobiLangDirectiveParser;


class IonicMobiLangDirectiveParser extends MobiLangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    @Override
    protected String replaceScreenDirectiveWith(String screenName) {
        return screenName;
    }

    @Override
    protected String replaceParamDirectiveWith(String paramName) {
        StringBuilder code = new StringBuilder();

        code.append("this.routeParams.snapshot.params.");
        code.append(paramName);
        code.append(';');

        return code.toString();
    }
}
