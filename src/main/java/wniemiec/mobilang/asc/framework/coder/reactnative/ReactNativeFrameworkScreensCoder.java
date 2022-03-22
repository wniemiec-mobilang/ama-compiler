package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.behavior.Behavior;
import wniemiec.io.java.BabelTranspiler;


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
    private final BabelTranspiler babelTranspiler;


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
        babelTranspiler = new BabelTranspiler(Consolex::writeError);
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

    private void putScript(List<String> code, ScreenData screenData) {
        code.add("    <script>");

        for (String line : parseBehavior(screenData.getBehavior())) {
            code.add(line);
        }

        code.add("    </script>");
    }

    private List<String> parseBehavior(Behavior behavior)  {
        List<String> lines = behavior.toCode();

        lines = parseBabel(lines);
        lines = parseDirectives(lines);

        return lines;
    }

    private List<String> parseBabel(List<String> code) {
        List<String> parsedCode = code;

        try {
            parsedCode = babelTranspiler.fromCode(code);
        } 
        catch (IOException e) {
            Consolex.writeWarning("Babel parser failed: " + e.toString());
        }

        return parsedCode;
    }

    private List<String> parseDirectives(List<String> code) {
        return directiveParser.parse(code);
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
