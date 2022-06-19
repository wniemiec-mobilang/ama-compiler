package wniemiec.mobilang.ama.framework.reactnative.app;

import java.nio.file.Path;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;
import wniemiec.mobilang.ama.util.io.FileManager;


class IosAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path sourceCodePath;
    private final Path mobileOutput;
    private final Terminal terminal;
    private final FileManager fileManager;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IosAppGenerator(
        Path sourceCodePath, 
        Path mobileOutput, 
        Terminal terminal,
        FileManager fileManager
    ) {
        this.sourceCodePath = sourceCodePath;
        this.mobileOutput = mobileOutput;
        this.terminal = terminal;
        this.fileManager = fileManager;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void generateApp() throws AppGenerationException {
        //Consolex.writeWarning("Ios app is not implemented yet");
    }
}
