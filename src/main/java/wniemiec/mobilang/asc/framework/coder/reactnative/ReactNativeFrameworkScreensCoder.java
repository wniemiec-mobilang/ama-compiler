package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.behavior.Variable;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for generating React Native framework code for screens.
 */
public class ReactNativeFrameworkScreensCoder extends FrameworkScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String SCREEN_NAME_PREFIX;
    private static final String SCREEN_NAME_SUFFIX;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        SCREEN_NAME_PREFIX = "src/screens/";
        SCREEN_NAME_SUFFIX = ".js";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeFrameworkScreensCoder(List<ScreenData> screensData) {
        super(screensData);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public List<FileCode> generateCode() {
        List<FileCode> screensCode = new ArrayList<>();

        for (ScreenData screenData : screensData) {
            screensCode.add(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private FileCode generateCodeForScreen(ScreenData screenData) {
        List<String> code = new ArrayList<>();

        putImports(code, screenData);
        putScreenDeclarations(code, screenData);
        putFunctionHeader(code, screenData);
        putStateDeclarations(code, screenData);
        putUseEffect(code, screenData);
        putFunctionReturn(code, screenData);
        putExportDefault(code, screenData);

        return buildFileCode(code, screenData);
    }

    private void putImports(List<String> code, ScreenData screenData) {
        for (String declaration : screenData.getImports()) {
            code.add(declaration + ";");
        }

        putNewLine(code);
        putNewLine(code);
    }

    private void putNewLine(List<String> code) {
        code.add("");
    }

    private void putScreenDeclarations(List<String> code, ScreenData screenData) {
        for (Variable declaration : screenData.getDeclarations()) {
            StringBuilder declarationCode = new StringBuilder();

            declarationCode.append(declaration.getKind());
            declarationCode.append(' ');
            declarationCode.append(declaration.getId());
            declarationCode.append(' ');
            declarationCode.append('=');
            declarationCode.append(' ');
            declarationCode.append(declaration.getContent());
            declarationCode.append(';');

            code.add(declarationCode.toString());
            putNewLine(code);
        }

        putNewLine(code);
    }


    private void putFunctionHeader(List<String> code, ScreenData screenData) {
        code.add("function " + screenData.getName() + "(props) {");
        
        putNewLine(code);
    }

    private void putStateDeclarations(List<String> code, ScreenData screenData) {
        for (Variable declaration : screenData.getStateDeclarations()) {
            StringBuilder declarationCode = new StringBuilder();

            declarationCode.append('[');
            declarationCode.append(declaration.getId());
            declarationCode.append(',');
            declarationCode.append("set" + StringUtils.capitalize(declaration.getId()));
            declarationCode.append(']');
            declarationCode.append(" = useState(");
            declarationCode.append(declaration.getContent());
            declarationCode.append(");");

            code.add(declarationCode.toString());
        }
        
        putNewLine(code);
    }

    private void putUseEffect(List<String> code, ScreenData screenData) {
        code.add("useEffect(() => {");
        
        for (String statement : screenData.getStateBody()) {
            code.add(statement + ";");
        }
        
        code.add("}, []);");
        
        putNewLine(code);
    }

    private void putFunctionReturn(List<String> code, ScreenData screenData) {
        code.add("return (");
        
        for (String statement : screenData.getBody()) {
            code.add(statement);
        }
        
        code.add(");");
        code.add("}");
        
        putNewLine(code);
    }


    private void putExportDefault(List<String> code, ScreenData screenData) {
        code.add("export default " + screenData.getName() + ";");
        
        putNewLine(code);
    }    

    private FileCode buildFileCode(List<String> code, ScreenData screenData) {
        String filename = generateScreenFilename(screenData);
        
        return new FileCode(filename, code);
    }

    private String generateScreenFilename(ScreenData screenData) {
        StringBuilder filename = new StringBuilder();

        filename.append(SCREEN_NAME_PREFIX);
        filename.append(screenData.getName());
        filename.append(SCREEN_NAME_SUFFIX);

        return filename.toString();
    }
}
