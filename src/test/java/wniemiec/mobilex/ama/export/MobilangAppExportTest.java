package wniemiec.mobilex.ama.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.framework.MockFramework;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class MobilangAppExportTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path TEMP_DIRECTORY;
    private MobilangAppExport appExport;
    private Framework framework;
    private Path generatedApp;
    private Path sourceCode;
    private Path output;
    private Set<String> platforms;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        TEMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir"));
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        framework = null;
        appExport = null;
        generatedApp = null;
        sourceCode = null;
        output = null;
        platforms = new HashSet<>();

        Consolex.setLoggerLevel(LogLevel.OFF);
    }

    @AfterEach
    void cleanUp() {
        Consolex.setLoggerLevel(LogLevel.INFO);
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testExportWithAllRequiredFields() 
    throws ParseException, IOException, CoderException, CodeExportException, 
    AppGenerationException {
        withFramework(new MockFramework());
        withSourceCode(Path.of("source"));
        withOutput(Path.of("mobilex"));
        withPlatforms("android", "ios");
        doAppExportation();
        assertAppWasExported();
    }

    @Test
    void testExportWithoutFramework() 
    throws ParseException, IOException, CoderException, CodeExportException, 
    AppGenerationException {
        withFramework(null);
        withSourceCode(Path.of("source"));
        withOutput(Path.of("mobilex"));
        withPlatforms("android", "ios");
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
            doAppExportation();
        });
    }

    @Test
    void testExportWithoutSourceCode() 
    throws ParseException, IOException, CoderException, CodeExportException, 
    AppGenerationException {
        withFramework(new MockFramework());
        withSourceCode(null);
        withOutput(Path.of("mobilex"));
        withPlatforms("android", "ios");
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
            doAppExportation();
        });
    }

    @Test
    void testExportWithoutOutput() 
    throws ParseException, IOException, CoderException, CodeExportException, 
    AppGenerationException {
        withFramework(new MockFramework());
        withSourceCode(Path.of("source"));
        withOutput(null);
        withPlatforms("android", "ios");
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
            doAppExportation();
        });
    }

    @Test
    void testExportWithoutPlatforms() 
    throws ParseException, IOException, CoderException, CodeExportException, 
    AppGenerationException {
        withFramework(new MockFramework());
        withSourceCode(Path.of("source"));
        withOutput(Path.of("mobilex"));
        withPlatforms((String[]) null);
        
        Assertions.assertThrows(IllegalStateException.class, () -> {
            doAppExportation();
        });
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withFramework(Framework framework) {
        this.framework = framework;
    }

    private void withSourceCode(Path path) {
        sourceCode = (path == null) ? null : TEMP_DIRECTORY.resolve(path);
    }

    private void withOutput(Path path) {
        output = (path == null) ? null : TEMP_DIRECTORY.resolve(path);
    }

    private void withPlatforms(String... platforms) {
        if (platforms == null) {
            this.platforms = null;
        }
        else {
            this.platforms.addAll(Arrays.asList(platforms));
        }
    }

    private void doAppExportation() throws AppGenerationException {
        appExport = new MobilangAppExport
            .Builder()
            .framework(framework)
            .sourceCode(sourceCode)
            .output(output)
            .platforms(platforms)
            .build();
        
        generatedApp = appExport.generateMobileApplications();
    }

    private void assertAppWasExported() {
        Assertions.assertTrue(Files.exists(generatedApp));
    }
}
