package wniemiec.mobilang.asc.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Responsible for parsing MobiLang directives in screen behavior.
 */
public abstract class MobiLangDirectiveParser {

    //-------------------------------------------------------------------------
    //      Attributes
    //-------------------------------------------------------------------------
    private List<String> parsedLines;

    
    //-------------------------------------------------------------------------
    //      Methods
    //-------------------------------------------------------------------------
    public final List<String> parse(List<String> lines) {
        parsedLines = new ArrayList<>();

        for (String line : lines) {
            parseLine(line);
        }

        return parsedLines;
    }

    private void parseLine(String line) {
        String parsedLine = line;

        if (isMobiLangDirective(line)) {
            parsedLine = parseMobiLangDirective(line);
        }

        parsedLines.add(parsedLine);
    }

    private boolean isMobiLangDirective(String line) {
        return line.contains("mobilang:");
    }

    private String parseMobiLangDirective(String line) {
        String parsedLine = line;

        if (isScreenDirective(line)) {
            parsedLine = parseScreenDirective(line);
        }
        else if (isParamDirective(line)) {
            parsedLine = parseParamDirective(line);
        }

        return parsedLine;
    }

    private boolean isScreenDirective(String line) {
        return line.contains("mobilang:screen:");
    }

    private String parseScreenDirective(String line) {
        Pattern pattern = Pattern.compile(".+mobilang:screen:([A-z0-9-_]+).+");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return line;
        }

        String screenName = matcher.group(1);
        String directive = "mobilang:screen:" + screenName;

        return line.replace(directive, replaceScreenDirectiveWith(screenName));
    }

    protected abstract String replaceScreenDirectiveWith(String screenName);

    private boolean isParamDirective(String line) {
        return line.contains("mobilang:param:");
    }

    private String parseParamDirective(String line) {
        Pattern pattern = Pattern.compile(".+mobilang:param:([A-z0-9-_]+).+");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return line;
        }

        String paramName = matcher.group(1);
        String directive = "\"mobilang:param:" + paramName + "\"";

        return line.replace(directive, replaceParamDirectiveWith(paramName));
    }

    protected abstract String replaceParamDirectiveWith(String paramName);
}
