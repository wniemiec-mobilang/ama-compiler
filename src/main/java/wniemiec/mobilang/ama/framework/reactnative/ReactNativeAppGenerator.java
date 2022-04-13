package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;


class ReactNativeAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path sourceCode;
    private final Path mobileOutput;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public ReactNativeAppGenerator(Path sourceCode, Path output) {
        mobileOutput = output.resolve("mobile");
        this.sourceCode = sourceCode;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Path generateMobileApplications() throws IOException {
        setUpOutput();
        generateAndroidApp();
        generateIosApp();

        return mobileOutput;
    }

    private void setUpOutput() throws IOException {
        FileUtils.deleteDirectory(mobileOutput.toFile());
        Files.createDirectories(mobileOutput);
    }

    private void generateAndroidApp() throws IOException {
        AndroidAppGenerator appGenerator = new AndroidAppGenerator(sourceCode, mobileOutput);
        
        Consolex.writeInfo("Generating Android app...");
        appGenerator.generateApp();
    }

    private void generateIosApp() throws IOException {
        IosAppGenerator appGenerator = new IosAppGenerator(sourceCode, mobileOutput);

        Consolex.writeInfo("Generating iOS app...");
        appGenerator.generateApp();
    }
}
