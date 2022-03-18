package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.behavior.Behavior;
import wniemiec.mobilang.asc.models.behavior.Variable;
import wniemiec.mobilang.asc.parser.babel.BabelParser;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for generating React Native framework code for screens.
 */
public class ReactNativeFrameworkScreensCoder extends FrameworkScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String ANDROID_SCREEN_NAME_PREFIX;
    private static final String IOS_SCREEN_NAME_PREFIX;
    private static final String SCREEN_NAME_SUFFIX;
    private final MobiLangDirectiveParser directiveParser;
    private final BabelParser babelParser;


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
    public ReactNativeFrameworkScreensCoder(List<ScreenData> screensData) {
        super(screensData);
        
        directiveParser = new MobiLangDirectiveParser();
        babelParser = new BabelParser();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public List<FileCode> generateCode() {
        List<FileCode> screensCode = new ArrayList<>();

        for (ScreenData screenData : screensData) {
            screensCode.addAll(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private List<FileCode> generateCodeForScreen(ScreenData screenData) {
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
        // pd ser empty
        code.addAll(screenData.getStyle());
        code.add("        </style>");
    }

    private void putBody(List<String> code, ScreenData screenData) {
        code.add("    <body>");

        List<String> structureCode = screenData.getStructure().toCode();
        List<String> parsedLines = directiveParser.parse(structureCode);

        code.addAll(parsedLines);

        code.add("    </body>");
    }

    private void putScript(List<String> code, ScreenData screenData) {
        code.add("    <script>");
    
        // pd ser empty
        for (String line : parseBehavior(screenData.getBehavior())) {
            code.add(line);
        }

        code.add("    </script>");
    }

    private List<String> parseBehavior(Behavior behavior) {
        List<String> lines = behavior.toCode();

        lines = babelParser.parse(lines);
        lines = directiveParser.parse(lines);

        return lines;
    }

    private void putHtmlCloseTag(List<String> code) {
        code.add("</html>");
    }

    private List<FileCode> buildFileCode(List<String> code, ScreenData screenData) {
        FileCode androidFileCode = generateAndroidScreenFileCode(code, screenData);
        FileCode iosFileCode = generateIosScreenFileCode(code, screenData);
        
        return List.of(androidFileCode, iosFileCode);
    }

    private FileCode generateAndroidScreenFileCode(List<String> code, ScreenData screenData) {
        String filename = generateScreenFilename(screenData, ANDROID_SCREEN_NAME_PREFIX);

        return new FileCode(filename, code);
    }

    private FileCode generateIosScreenFileCode(List<String> code, ScreenData screenData) {
        String filename = generateScreenFilename(screenData, IOS_SCREEN_NAME_PREFIX);

        return new FileCode(filename, code);
    }

    private String generateScreenFilename(ScreenData screenData, String prefix) {
        StringBuilder filename = new StringBuilder();

        filename.append(prefix);
        filename.append(screenData.getName());
        filename.append(SCREEN_NAME_SUFFIX);

        return filename.toString();
    }
}
