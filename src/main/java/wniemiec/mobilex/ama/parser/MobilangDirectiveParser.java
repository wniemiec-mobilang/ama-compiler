package wniemiec.mobilex.ama.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.mobilex.ama.util.data.Validator;


/**
 * Responsible for parsing MobiLang directives in screen behavior.
 */
public abstract class MobilangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Attributes
    //-------------------------------------------------------------------------
    private List<String> parsedLines;
    private List<String> screenParameters;


    //-------------------------------------------------------------------------
    //      Constructor
    //-------------------------------------------------------------------------
    protected MobilangDirectiveParser() {
        parsedLines = new ArrayList<>();
        screenParameters = new ArrayList<>();
    }

    
    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    public final String parse(String line) {
        Validator.validateLine(line);

        parse(List.of(line));

        return parsedLines.get(0);
    }

    public final void parse(List<String> lines) {
        Validator.validateLines(lines);

        parsedLines = new ArrayList<>();
        screenParameters = new ArrayList<>();

        for (String line : lines) {
            parseLine(line);
        }
    }

    private void parseLine(String line) {
        String parsedLine = line;

        if (isMobiLangDirective(line)) {
            parsedLine = parseMobiLangDirective(line);
        }

        parsedLines.add(parsedLine);
    }

    private boolean isMobiLangDirective(String line) {
        return line.contains("mobilang::");
    }

    private String parseMobiLangDirective(String line) {
        String parsedLine = line;

        if (isScreenDirective(line)) {
            parsedLine = parseScreenDirective(line);
        }
        else if (isParamDirective(line)) {
            parsedLine = parseParamDirective(line);
        }
        else if (isInputDirective(line)) {
            parsedLine = parseInputDirective(line);
        }

        return parsedLine;
    }

    private boolean isScreenDirective(String line) {
        return line.contains("mobilang::screen::");
    }

    private String parseScreenDirective(String line) {
        if (line.matches(".*mobilang::screen::([A-z0-9-_]+\\?).*")) {
            return parseScreenDirectiveWithParameters(line);
        }

        return parseScreenDirectiveWithoutParameters(line);
    }

    private String parseScreenDirectiveWithParameters(String line) {
        String normalizedLine = line.replaceAll("[ +\"\']+", "");
        Pattern pattern = Pattern.compile(".*mobilang::screen::([A-z0-9\\-_]+)\\?([^?\"'\\/\\\\]+)([\"']+|$).*");
        Matcher matcher = pattern.matcher(normalizedLine);

        if (!matcher.matches()) {
            return normalizedLine;
        }

        String screenName = matcher.group(1);
        Map<String, String> parameters = new HashMap<>();        
        String rawParameters = matcher.group(2); // ex: id=" + data[item].id + "&q=123&f=" + data[item].f + "

        for (String rawParameter : rawParameters.split("&")) {
            String[] terms = rawParameter.split("=");
            
            parameters.put(terms[0], terms[1]);
            screenParameters.add(terms[0]);
        }

        String quote = extractDirectiveQuoteFrom(normalizedLine);
        String directive = quote + "mobilang::screen::([A-z0-9\\-_]+\\?)[^\\\\]+" + quote;
        String replacement = quote + swapScreenDirectiveWithParametersFor(screenName, parameters) + quote;

        return normalizedLine.replaceAll(directive, replacement.replace("$", "\\$"));
    }

    private String extractDirectiveQuoteFrom(String directive) {
        String quote = "";
        int indexOfMobilang = directive.indexOf("mobilang");

        if (indexOfMobilang > 0) {
            String directiveBegin = directive.substring(indexOfMobilang-1, indexOfMobilang);
            quote = directiveBegin.equals("\"") ? "\"" : "'";
        }

        return quote;
    }

    private String cleanRawParameters(String rawParameters) {
        // in:  id=" + data[item].id + "&q=123&f=" + data[item].f + "
        // out: id=data[item].id&q=123&f=data[item].f
        String cleanedParameters = rawParameters;

        cleanedParameters = removeWhiteSpaces(cleanedParameters);
        cleanedParameters = removeStringConcatenationTokens(cleanedParameters);

        return cleanedParameters;
    }

    private String removeWhiteSpaces(String text) {
        return text.replace(" ", "");
    }

    private String removeStringConcatenationTokens(String text) {
        return text.replaceAll("((\"\\+)|(\\+\"))", "");
    }

    protected abstract String swapScreenDirectiveWithParametersFor(String screenName, Map<String, String> parameters);

    private String parseScreenDirectiveWithoutParameters(String line) {
        Pattern pattern = Pattern.compile(".*mobilang::screen::([A-z0-9-_]+).*");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return line;
        }

        String screenName = matcher.group(1);
        String directive = "mobilang::screen::" + screenName;

        return line.replace(directive, swapScreenDirectiveFor(screenName));
    }

    protected abstract String swapScreenDirectiveFor(String screenName);

    private boolean isParamDirective(String line) {
        return line.contains("mobilang::param::");
    }

    private String parseParamDirective(String line) {
        Pattern pattern = Pattern.compile(".*mobilang::param::([A-z0-9-_]+).*");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return line;
        }

        String paramName = matcher.group(1);
        String directive = "[\"']?mobilang::param::" + paramName + "[\"']?";

        return line.replaceAll(directive, replaceParamDirectiveWith(paramName));
    }

    protected abstract String replaceParamDirectiveWith(String paramName);

    private boolean isInputDirective(String line) {
        return line.contains("mobilang::input::");
    }

    private String parseInputDirective(String line) {
        Pattern pattern = Pattern.compile(".*mobilang::input::([A-z0-9-_]+).*");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return line;
        }

        String inputId = matcher.group(1);
        String directive = "[\"']?mobilang::input::" + inputId + "[\"']?";

        return line.replaceAll(directive, swapInputDirectiveFor(inputId));
    }

    protected abstract String swapInputDirectiveFor(String inputId);


    //-------------------------------------------------------------------------
    //      Getters
    //-------------------------------------------------------------------------
    public List<String> getScreenParameters() {
        return screenParameters;
    }

    public List<String> getParsedCode() {
        return parsedLines;
    }
}
