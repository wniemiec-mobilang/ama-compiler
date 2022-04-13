package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Path;
import wniemiec.io.java.Consolex;


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
    public void generateApp() throws IOException {
        Consolex.writeInfo("Ios app is not implemented yet");
    }
}
