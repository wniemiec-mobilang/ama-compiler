package wniemiec.mobilang.ama.framework.reactnative;

import java.nio.file.Path;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;


class IosAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path sourceCodePath;
    private final Path mobileOutput;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IosAppGenerator(Path sourceCodePath, Path mobileOutput) {
        this.sourceCodePath = sourceCodePath;
        this.mobileOutput = mobileOutput;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void generateApp() throws AppGenerationException {
        Consolex.writeWarning("Ios app is not implemented yet");
    }
}
