package wniemiec.mobilex.ama.framework.reactnative.coder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import wniemiec.io.java.BabelTranspiler;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.framework.reactnative.parser.ReactNativeMobilangDirectiveParser;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.models.behavior.Behavior;


/**
 * Responsible for generating React Native framework code for screens.
 */
public class ReactNativeScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String ANDROID_SCREEN_NAME_PREFIX;
    private static final String IOS_SCREEN_NAME_PREFIX;
    private static final String SCREEN_NAME_SUFFIX;
    private final List<Screen> screensData;
    private final ReactNativeMobilangDirectiveParser directiveParser;
    private final BabelTranspiler babelTranspiler;
    private List<String> babelErrorLog;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        ANDROID_SCREEN_NAME_PREFIX = "android/app/src/main/assets/";
        IOS_SCREEN_NAME_PREFIX = "ios/assets/";
        SCREEN_NAME_SUFFIX = ".html";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeScreensCoder(List<Screen> screensData) {
        this.screensData = screensData;
        babelErrorLog = new ArrayList<>();
        directiveParser = new ReactNativeMobilangDirectiveParser();
        babelTranspiler = new BabelTranspiler(babelErrorLog::add);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() throws CoderException {
        List<CodeFile> screensCode = new ArrayList<>();

        for (Screen screenData : screensData) {
            screensCode.addAll(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private List<CodeFile> generateCodeForScreen(Screen screenData) throws CoderException {
        List<String> code = new ArrayList<>();

        putDoctype(code);
        putHtmlOpenTag(code);
        putHead(code, screenData);
        putBody(code, screenData);
        putScript(code, screenData);
        putHtmlCloseTag(code);
        
        return buildFileCode(code, screenData);
    }

    private void putDoctype(List<String> code) {
        code.add("<!DOCTYPE html>");
    }

    private void putHtmlOpenTag(List<String> code) {
        code.add("<html>");
    }

    private void putHead(List<String> code, Screen screenData) {
        code.add("    <head>");
        code.add("    <title>" + screenData.getRawName() + "</title>");
        putStyle(code, screenData);
        code.add("    </head>");
    }

    private void putStyle(List<String> code, Screen screenData) {
        code.add("        <style>");
        code.add("button { padding: 0; }");
        code.addAll(screenData.getStyle().toCode());
        code.add("        </style>");
    }

    private void putBody(List<String> code, Screen screenData) {
        code.add("    <body>");
        code.addAll(parseStructure(screenData));
        code.add("    </body>");
    }


    private List<String> parseStructure(Screen screenData) {
        List<String> structureCode = screenData.getStructure().toCode();
        
        return parseDirectives(structureCode);
    }

    private void putScript(List<String> code, Screen screenData) throws CoderException {
        code.add("    <script>");

        for (String line : parseBehavior(screenData.getBehavior())) {
            code.add(line);
        }

        code.add("    </script>");
    }

    private List<String> parseBehavior(Behavior behavior) throws CoderException  {
        List<String> lines = behavior.toCode();

        lines = parseBabel(lines);
        lines = parseDirectives(lines);

        return lines;
    }

    private List<String> parseBabel(List<String> code) throws CoderException {
        List<String> parsedCode = runBabelTranspiler(code);

        babelErrorLog = babelErrorLog.stream().filter(message -> !message.contains("npm notice")).collect(Collectors.toList());

        if (!babelErrorLog.isEmpty()) {
            throw new CoderException(babelErrorLog);
        }

        return parsedCode;
    }


    private List<String> runBabelTranspiler(List<String> code) throws CoderException {
        try {
            return babelTranspiler.fromCode(code);
        } 
        catch (IOException e) {
            throw new CoderException(e.getMessage());
        }
    }

    private List<String> parseDirectives(List<String> code) {
        directiveParser.parse(code);
        return directiveParser.getParsedCode();
    }

    private void putHtmlCloseTag(List<String> code) {
        code.add("</html>");
    }

    private List<CodeFile> buildFileCode(List<String> code, Screen screenData) {
        CodeFile androidFileCode = generateAndroidScreenFileCode(code, screenData);
        CodeFile iosFileCode = generateIosScreenFileCode(code, screenData);
        
        return List.of(androidFileCode, iosFileCode);
    }

    private CodeFile generateAndroidScreenFileCode(List<String> code, Screen screenData) {
        String filename = generateScreenFilename(screenData, ANDROID_SCREEN_NAME_PREFIX);

        return new CodeFile(filename, code);
    }

    private CodeFile generateIosScreenFileCode(List<String> code, Screen screenData) {
        String filename = generateScreenFilename(screenData, IOS_SCREEN_NAME_PREFIX);

        return new CodeFile(filename, code);
    }

    private String generateScreenFilename(Screen screenData, String prefix) {
        StringBuilder filename = new StringBuilder();

        filename.append(prefix);
        filename.append(screenData.getRawName());
        filename.append(SCREEN_NAME_SUFFIX);

        return filename.toString();
    }
}
