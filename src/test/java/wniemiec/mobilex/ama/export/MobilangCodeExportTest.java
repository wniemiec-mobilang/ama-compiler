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
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.MockFramework;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.parser.MobilangAstParser;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.MobilangDotReader;


class MobilangCodeExportTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private static final Path TEMP_DIRECTORY;
    private MobilangDotReader dotReader;
    private MobilangAstParser parser;
    private MobilangCodeExport codeExport;
    private Path codeOutput;
    private MockFramework framework;
    private Project generatedProject;


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
    throws ParseException, IOException, CoderException, CodeExportException {
        withAst("HelloWorld.dot");
        doParsing();
        doCodeGeneration();
        doCodeExportation();
        assertProjectWasCreated();
        assertCodeWasExported();
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
        codeExport = new MobilangCodeExport
            .Builder()
            .properties(parser.getProperties())
            .dependencies(generatedProject.getDependencies())
            .codeFiles(generatedProject.getCodeFiles())
            .framework(framework)
            .output(TEMP_DIRECTORY.resolve("mobilex"))
            .build();

        codeOutput = codeExport.export();
    }

    private void assertProjectWasCreated() {
        Assertions.assertTrue(framework.wasProjectCreated());
    }

    private void assertCodeWasExported() {
        Assertions.assertTrue(Files.exists(codeOutput));
    }
}
