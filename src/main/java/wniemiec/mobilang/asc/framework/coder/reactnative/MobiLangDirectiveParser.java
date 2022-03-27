package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wniemiec.util.java.StringUtils;


/**
 * Responsible for parsing MobiLang directives in screen behavior.
 */
class MobiLangDirectiveParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> parsedLines;


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<String> parse(List<String> lines) {
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
        String normalizedScreenName = StringUtils.capitalize(screenName) + "Screen";

        return line.replace("mobilang:screen:" + screenName, normalizedScreenName + ".html");
    }
}
