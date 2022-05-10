package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import wniemiec.io.java.BabelTranspiler;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.ScreenData;
import wniemiec.mobilang.ama.models.behavior.Behavior;


/**
 * Responsible for generating React Native framework code for screens.
 */
class ReactNativeScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String ANDROID_SCREEN_NAME_PREFIX;
    private static final String IOS_SCREEN_NAME_PREFIX;
    private static final String SCREEN_NAME_SUFFIX;
    private final List<ScreenData> screensData;
    private final ReactNativeMobiLangDirectiveParser directiveParser;
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
    public ReactNativeScreensCoder(List<ScreenData> screensData) {
        this.screensData = screensData;
        babelErrorLog = new ArrayList<>();
        directiveParser = new ReactNativeMobiLangDirectiveParser();
        babelTranspiler = new BabelTranspiler(babelErrorLog::add);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() throws CoderException {
        List<CodeFile> screensCode = new ArrayList<>();

        for (ScreenData screenData : screensData) {
            screensCode.addAll(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private List<CodeFile> generateCodeForScreen(ScreenData screenData) throws CoderException {
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

    private void putHead(List<String> code, ScreenData screenData) {
        code.add("    <head>");
        code.add("    <title>" + screenData.getName() + "</title>");
        putStyle(code, screenData);
        code.add("    </head>");
    }

    private void putStyle(List<String> code, ScreenData screenData) {
        code.add("        <style>");
        code.addAll(screenData.getStyle().toCode());
        code.add("        </style>");
    }

    private void putBody(List<String> code, ScreenData screenData) {
        code.add("    <body>");
        code.addAll(parseStructure(screenData));
        code.add("    </body>");
    }


    private List<String> parseStructure(ScreenData screenData) {
        List<String> structureCode = screenData.getStructure().toCode();
        
        return parseDirectives(structureCode);
    }

    private void putScript(List<String> code, ScreenData screenData) throws CoderException {
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

    private List<CodeFile> buildFileCode(List<String> code, ScreenData screenData) {
        CodeFile androidFileCode = generateAndroidScreenFileCode(code, screenData);
        CodeFile iosFileCode = generateIosScreenFileCode(code, screenData);
        
        return List.of(androidFileCode, iosFileCode);
    }

    private CodeFile generateAndroidScreenFileCode(List<String> code, ScreenData screenData) {
        String filename = generateScreenFilename(screenData, ANDROID_SCREEN_NAME_PREFIX);

        return new CodeFile(filename, code);
    }

    private CodeFile generateIosScreenFileCode(List<String> code, ScreenData screenData) {
        String filename = generateScreenFilename(screenData, IOS_SCREEN_NAME_PREFIX);

        return new CodeFile(filename, code);
    }

    private String generateScreenFilename(ScreenData screenData, String prefix) {
        StringBuilder filename = new StringBuilder();

        filename.append(prefix);
        filename.append(screenData.getName());
        filename.append(SCREEN_NAME_SUFFIX);

        return filename.toString();
    }
}
