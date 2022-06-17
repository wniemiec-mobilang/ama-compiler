package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Map;
import wniemiec.mobilang.ama.parser.MobiLangDirectiveParser;


class IonicMobiLangDirectiveParser extends MobiLangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    @Override
    protected String swapScreenDirectiveFor(String screenName) {
        return screenName;
    }

    @Override
    protected String swapScreenDirectiveWithParametersFor(String screenName, Map<String, String> parameters) {
        // TODO: replace "mobilang::screen::glossary-desc?id=${data[item].id}" by "glossary-desc/${data[item].id}"
        // TODO: update routing: screen parameters
        StringBuilder code = new StringBuilder();

        code.append(screenName);
        code.append('/');
        
        parameters.forEach((key, value) -> {
            code.append(key);
            code.append("__eq__");
            code.append(value);
            code.append('&');
        });

        code.deleteCharAt(code.length()-1); // Removes last '&'

        return code.toString();
    }

    @Override
    protected String replaceParamDirectiveWith(String paramName) {
        StringBuilder code = new StringBuilder();

        code.append("this.routeParams.snapshot.params.q.split('");
        code.append(paramName);
        code.append("__eq__')[1].split('&')[0]");

        return code.toString();
    }

    @Override
    protected String swapInputDirectiveFor(String inputId) {
        String normalizedId = inputId.replace("-", "_");
        StringBuilder code = new StringBuilder();
        
        code.append("this.__input_");
        code.append(normalizedId);

        return code.toString();
    }
}
