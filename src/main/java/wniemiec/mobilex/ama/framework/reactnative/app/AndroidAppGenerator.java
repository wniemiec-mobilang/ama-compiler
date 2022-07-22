package wniemiec.mobilex.ama.framework.reactnative.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.util.data.Validator;
import wniemiec.mobilex.ama.util.io.FileManager;


class AndroidAppGenerator {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String KEYSTORE_PASSWORD;
    private final Path androidProjectPath;
    private final Path androidOutput;
    private final String appName;
    private final Terminal terminal;
    private final FileManager fileManager;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        KEYSTORE_PASSWORD = "abcdef12"; // TEMP
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public AndroidAppGenerator(
        Path sourceCode, 
        Path output, 
        Terminal terminal,
        FileManager fileManager
    ) {
        Validator.validateSource(sourceCode);
        Validator.validateOutput(output);
        Validator.validateTerminal(terminal);
        Validator.validateFileManager(fileManager);
        
        androidProjectPath = sourceCode.resolve("android").normalize();
        androidOutput = output.resolve("android");
        appName =  extractAppName(sourceCode);
        this.terminal = terminal;
        this.fileManager = fileManager;
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private String extractAppName(Path srcPath) {
        if (srcPath.getParent() == null) {
            return srcPath.getFileName().toString();
        }

        return srcPath.getParent().getFileName().toString();
    }

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
        updateBuildGradle();
        updateGradlePropertiesFromAppFolder();
        updateGradleBuildFromAppFolder();
        runGradlew();
        setUpOutputLocation();
        moveAabToOutputLocation();

        Consolex.writeInfo("Android app generated!");
    }

	private void generateKeyStore() throws IOException {
        Path javaBinPath = Path.of(System.getProperty("java.home"), "bin");
        Path keystore = androidProjectPath.resolve(Path.of("app", appName + ".keystore"));

        fileManager.removeFile(keystore);

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

    private void updateBuildGradle() throws IOException {
        Path gradleFile = androidProjectPath.resolve("build.gradle");
        List<String> gradleFileContent = fileManager.readLines(gradleFile);

        if (gradleFileContent.size() > 0) {
            Consolex.writeDebug("Updating build gradle...");

            gradleFileContent.add(10, "kotlinVersion = \"1.6.0\"");
    
            fileManager.write(gradleFile, gradleFileContent);
        }
	}

	private void updateGradlePropertiesFromAppFolder() throws IOException {
        Path gradleFile = androidProjectPath.resolve("gradle.properties");
        List<String> gradleFileContent = fileManager.readLines(gradleFile);

        
        if (gradleFileContent.size() < 2 || !gradleFileContent.get(gradleFileContent.size()-2).startsWith("MYAPP_")) {
            Consolex.writeDebug("Updating gradle properties...");
            List<String> lines = new ArrayList<>();
    
            lines.add("MYAPP_UPLOAD_STORE_FILE=" + appName + ".keystore");
            lines.add("MYAPP_UPLOAD_KEY_ALIAS=" + appName);
            lines.add("MYAPP_UPLOAD_STORE_PASSWORD=" + KEYSTORE_PASSWORD);
            lines.add("MYAPP_UPLOAD_KEY_PASSWORD=" + KEYSTORE_PASSWORD);
            lines.add("org.gradle.jvmargs=-Xmx4608m");
    
            fileManager.append(gradleFile, lines);
        }
	}

	private void updateGradleBuildFromAppFolder() throws IOException {
        Path gradleFile = androidProjectPath.resolve(Path.of("app", "build.gradle"));
        List<String> gradleBuild = fileManager.readLines(gradleFile);

        if (gradleBuild.isEmpty() || !gradleBuild.get(0).matches("// Modified by SCMA")) {
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

            fileManager.write(gradleFile, updatedGradleBuild);
        }

	}

	private void runGradlew() throws IOException {
        Consolex.writeDebug("Running gradlew...");

        terminal.exec(
            androidProjectPath.resolve("gradlew").toString(),
            "-p",
            androidProjectPath.toString(),
            "bundleRelease"
        );
	}

    private void setUpOutputLocation() throws IOException {
        if (fileManager.exists(androidOutput)) {
            return;
        }

        Consolex.writeDebug("Setting up output location...");

        fileManager.createDirectories(androidOutput);
    }

	private void moveAabToOutputLocation() throws IOException {
        Path aab = androidProjectPath.resolve(buildAabLocationPath());

        Consolex.writeDebug("Moving android app to output location...");
        
        fileManager.copy(aab, buildAndroidOutputAabPath());
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
