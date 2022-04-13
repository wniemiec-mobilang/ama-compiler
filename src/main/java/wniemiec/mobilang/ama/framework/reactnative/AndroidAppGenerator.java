package wniemiec.mobilang.ama.framework.reactnative;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.StandardTerminalBuilder;
import wniemiec.io.java.Terminal;
import wniemiec.mobilang.ama.export.exception.AppGenerationException;


class AndroidAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String KEYSTORE_PASSWORD;
    private final Path androidProjectPath;
    private final Path androidOutput;
    private final String appName;
    

    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        KEYSTORE_PASSWORD = "abcdef12"; // TEMP
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public AndroidAppGenerator(Path sourceCodePath, Path mobileOutput) {
        androidProjectPath = sourceCodePath.resolve("android").normalize();
        androidOutput = mobileOutput.resolve("android");
        appName = sourceCodePath.getParent().getFileName().toString();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void generateApp() throws AppGenerationException {
        try {
            generateAndroidApp();
        } 
        catch (IOException e) {
            throw new AppGenerationException(e.toString());
        }
    }

    private void generateAndroidApp() throws IOException {
        generateKeyStore();
        updateGradlePropertiesFromAppFolder();
        updateGradleBuildFromAppFolder();
        runGradlew();
        setUpOutputLocation();
        moveAabToOutputLocation();

        Consolex.writeInfo("Android app generated!");
    }

	private void generateKeyStore() throws IOException {
        Path javaBinPath = Path.of(System.getProperty("java.home"), "bin");
        Terminal terminal = buildTerminalWithErrorsMuted();
        Path keystore = androidProjectPath.resolve(Path.of("app", appName + ".keystore"));

        Files.deleteIfExists(keystore);

        Consolex.writeDebug("Generating keystore...");

        terminal.exec(
            javaBinPath.resolve("keytool").toString(),
            "-genkeypair",
            "-v",
            "-keystore",
            keystore.toString(),
            "-alias",
            appName,
            "-keyalg",
            "RSA",
            "-keysize",
            "2048",
            "-validity",
            "10000",
            "-dname",
            "cn=Unknown,ou=Unknown,o=Unknown,c=Unknown",
            "-storepass",
            KEYSTORE_PASSWORD,
            "-keypass",
            KEYSTORE_PASSWORD
        );
	}

    private Terminal buildTerminalWithErrorsMuted() {
        return StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
    }

	private void updateGradlePropertiesFromAppFolder() throws IOException {
        Path gradleFile = androidProjectPath.resolve("gradle.properties");
        List<String> gradleFileContent = Files.readAllLines(gradleFile);

        
        if (!gradleFileContent.get(gradleFileContent.size()-2).startsWith("MYAPP_")) {
            Consolex.writeDebug("Updating gradle properties...");
            List<String> lines = new ArrayList<>();
    
            lines.add("MYAPP_UPLOAD_STORE_FILE=" + appName + ".keystore");
            lines.add("MYAPP_UPLOAD_KEY_ALIAS=" + appName);
            lines.add("MYAPP_UPLOAD_STORE_PASSWORD=" + KEYSTORE_PASSWORD);
            lines.add("MYAPP_UPLOAD_KEY_PASSWORD=" + KEYSTORE_PASSWORD);
            lines.add("org.gradle.jvmargs=-Xmx4608m");
    
            Files.write(gradleFile, lines, Charset.defaultCharset(), StandardOpenOption.APPEND);
        }
	}

	private void updateGradleBuildFromAppFolder() throws IOException {
        Path gradleFile = androidProjectPath.resolve(Path.of("app", "build.gradle"));
        List<String> gradleBuild = Files.readAllLines(gradleFile);

        if (!gradleBuild.get(0).matches("// Modified by SCMA")) {
            Consolex.writeDebug("Updating gradle build from app folder...");
            List<String> updatedGradleBuild = new ArrayList<>();
    
            updatedGradleBuild.add("// Modified by SCMA");
    
            for (int i=0; i < gradleBuild.size(); i++) {
    
                if (gradleBuild.get(i).contains("signingConfigs {")) {
                    updatedGradleBuild.add(gradleBuild.get(i));
                    updatedGradleBuild.addAll(Arrays.asList(
                       "release {",
                       "  if (project.hasProperty('MYAPP_UPLOAD_STORE_FILE')) {",
                       "    storeFile file(MYAPP_UPLOAD_STORE_FILE)",
                       "    storePassword MYAPP_UPLOAD_STORE_PASSWORD",
                       "    keyAlias MYAPP_UPLOAD_KEY_ALIAS",
                       "    keyPassword MYAPP_UPLOAD_KEY_PASSWORD",
                       "  }",
                       "}"
                    ));
                }
                else if (gradleBuild.get(i).contains("signingConfigs.debug") && !gradleBuild.get(i-1).contains("debug {")) {
                    updatedGradleBuild.add(gradleBuild.get(i).replace("debug", "release"));
                }
                else {
                    updatedGradleBuild.add(gradleBuild.get(i));
                }
            }
            Files.write(gradleFile, updatedGradleBuild, Charset.defaultCharset(), StandardOpenOption.WRITE);
        }

	}

	private void runGradlew() throws IOException {
        Terminal terminal = buildTerminal();

        Consolex.writeDebug("Running gradlew...");

        terminal.exec(
            androidProjectPath.resolve("gradlew").toString(),
            "-p",
            androidProjectPath.toString(),
            "bundleRelease"
        );
	}

    private Terminal buildTerminal() {
        return StandardTerminalBuilder
            .getInstance()
            .outputHandler(Consolex::writeDebug)
            .outputErrorHandler(Consolex::writeDebug)
            .build();
    }

    private void setUpOutputLocation() throws IOException {
        if (Files.exists(androidOutput)) {
            return;
        }

        Consolex.writeDebug("Setting up output location...");

        Files.createDirectories(androidOutput);
    }

	private void moveAabToOutputLocation() throws IOException {
        Path aab = androidProjectPath.resolve(buildAabLocationPath());

        Consolex.writeDebug("Moving android app to output location...");
        
        Files.copy(aab, buildAndroidOutputAabPath());
	}

    private Path buildAndroidOutputAabPath() {
        return androidOutput.resolve(appName + ".aab");
    }

    private Path buildAabLocationPath() {
        return Path.of(
            "app", 
            "build", 
            "outputs", 
            "bundle", 
            "release", 
            "app-release.aab"
        );
    }
}
