package wniemiec.mobilang.ama.framework.ionic;

import java.nio.file.Path;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;

// ANDROID
// npm install @capacitor/core
// ionic build
// ionic capacitor add android
// ionic capacitor build android


/**
 * Responsible for generating mobile applications through Ionic framework.
 */
class IonicAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Path sourceCode;
    private final Path mobileOutput;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicAppGenerator(Path sourceCode, Path output) {
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
            throw new AppGenerationException("There is no compatibility with " +
                                             "this mobile platform: " + platform);
        }

        return mobileOutput;
    }

    private void generateAndroidApp() throws AppGenerationException {
        AndroidAppGenerator appGenerator = new AndroidAppGenerator(
            sourceCode, 
            mobileOutput
        );

        appGenerator.generateApp();
    }

    private void generateIosApp() throws AppGenerationException {
        IosAppGenerator appGenerator = new IosAppGenerator(
            sourceCode, mobileOutput
        );
        appGenerator.generateApp();
    }
}