package wniemiec.mobilex.ama.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.MockFramework;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.parser.MobilangAstParser;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.MobilangDotReader;


class MobilangAppExportTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private static final Path TEMP_DIRECTORY;
    private MobilangDotReader dotReader;
    private MobilangAstParser parser;
    private MobilangCodeExport codeExport;
    private MobilangAppExport appExport;
    private Path codeOutput;
    private MockFramework framework;
    private Project generatedProject;
    private Path generatedApp;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        RESOURCES = Path.of(".", "src", "test", "resources");
        TEMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir"));
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        dotReader = new MobilangDotReader();
        parser = null;
        codeOutput = null;
        framework = new MockFramework();
        codeExport = null;
        generatedProject = null;
        appExport = null;
        generatedApp = null;

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
    throws ParseException, IOException, CoderException, CodeExportException, AppGenerationException {
        withAst("HelloWorld.dot");
        doParsing();
        doCodeGeneration();
        doCodeExportation();
        doAppExportation();
        assertAppWasExported();
        assertAppWasGenerated();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAst(String file) throws FileNotFoundException {
        dotReader.read(RESOURCES.resolve(file));
    }

    private void doParsing() throws ParseException, IOException {
        parser = new MobilangAstParser(dotReader.getTree());
        
        parser.parse();
    }

    private void doCodeGeneration() throws CoderException {
        generatedProject = framework.generateCode(parser.getScreens());
    }

    private void doCodeExportation() throws CodeExportException {
        codeExport = new MobilangCodeExport(
            parser.getProperties(), 
            generatedProject.getCodeFiles(), 
            generatedProject.getDependencies(), 
            framework, 
            TEMP_DIRECTORY.resolve("mobilex")
        );

        codeOutput = codeExport.export();
    }

    private void doAppExportation() throws AppGenerationException {
        appExport = new MobilangAppExport(framework, codeOutput, TEMP_DIRECTORY.resolve("mobilex"), parser.getProperties().getTargetPlatforms());

        generatedApp = appExport.generateMobileApplications();
    }

    private void assertAppWasExported() {
        Assertions.assertTrue(Files.exists(generatedApp));
    }

    private void assertAppWasGenerated() {
        parser.getProperties().getTargetPlatforms().forEach(platform -> {
            Assertions.assertTrue(framework.wasGeneratedMobileApplicationFor(platform));
        });
    }
}
