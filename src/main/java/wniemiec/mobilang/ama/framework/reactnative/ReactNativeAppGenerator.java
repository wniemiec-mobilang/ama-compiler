package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;


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
    public Path generateMobileApplicationFor(String platform) 
    throws AppGenerationException {
        String normalizedPlatform = platform.toLowerCase();

        if (normalizedPlatform.equals("android")) {
            generateAndroidApp();
        }
        else if (normalizedPlatform.contains("ios")) {
            generateIosApp();
        }
        else {
            throw new AppGenerationException("There is no compatibility with this mobile platform: " + platform);
        }

        return mobileOutput;
    }

    private void generateAndroidApp() throws AppGenerationException {
        AndroidAppGenerator appGenerator = new AndroidAppGenerator(sourceCode, mobileOutput);
        
        Consolex.writeInfo("Generating Android app...");
        appGenerator.generateApp();
    }

    private void generateIosApp() throws AppGenerationException {
        IosAppGenerator appGenerator = new IosAppGenerator(sourceCode, mobileOutput);

        Consolex.writeInfo("Generating iOS app...");
        appGenerator.generateApp();
    }
}
