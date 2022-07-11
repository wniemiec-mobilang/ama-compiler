package wniemiec.mobilex.ama.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.CodeExportException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.framework.MockFramework;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.parser.MobilangAstParser;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.MobilangDotReader;


class MobilangCodeExportTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private static final Path TEMP_DIRECTORY;
    private MobilangCodeExport codeExport;
    private Path output;
    private Framework framework;
    private Properties properties;
    private Properties builtProperties;
    private Set<String> dependencies;
    private Set<String> builtDependencies;
    private List<CodeFile> codeFiles;


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
        output = null;
        framework = null;
        codeExport = null;
        properties = null;
        dependencies = null;
        codeFiles = null;
        builtProperties = new Properties();
        builtDependencies = new HashSet<>();

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
        withFramework(new MockFramework());
        withOutput(Path.of("mobilex"));
        withPropertyApplicationName("something");
        withPropertyPlatforms("android", "ios");
        withProperties(builtProperties);
        withDependency("foo/bar");
        withDependencies(builtDependencies);
        withCodeFiles(readCodeFilesFromAst("HelloWorld.dot"));
        doCodeExportation();
        assertCodeWasExported();
    }

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withFramework(Framework framework) {
        this.framework = framework;
    }
    
    private void withOutput(Path path) {
        output = (path == null) ? null : TEMP_DIRECTORY.resolve(path);
    }

    private void withPropertyApplicationName(String name) {
        builtProperties.setApplicationName(name);
    }

    private void withPropertyPlatforms(String... platforms) {
        Arrays.stream(platforms).forEach(builtProperties::addPlatform);
    }
    
    private void withProperties(Properties properties) {
        this.properties = properties;
    }

    private void withDependency(String dependency) {
        builtDependencies.add(dependency);
    }

    private void withDependencies(Set<String> dependencies) {
        this.dependencies = dependencies;
    }

    private void withCodeFiles(List<CodeFile> codeFiles) {
        this.codeFiles = codeFiles;
    }
    
    private List<CodeFile> readCodeFilesFromAst(String mobilangAst) 
    throws CoderException, ParseException, IOException {
        SortedMap<String, List<Node>> ast = readAst(mobilangAst);
        List<Screen> screens = parseAst(ast);

        return generateFrameworkCodeFiles(screens);
    }

    private SortedMap<String, List<Node>> readAst(String mobilangAst) 
    throws FileNotFoundException {
        MobilangDotReader reader = new MobilangDotReader();
        
        reader.read(RESOURCES.resolve(mobilangAst));
        
        return reader.getTree();
    }

    private List<Screen> parseAst(SortedMap<String, List<Node>> ast) 
    throws ParseException, IOException {
        MobilangAstParser parser = new MobilangAstParser(ast);
        
        parser.parse();
        
        return parser.getScreens();
    }

    private List<CodeFile> generateFrameworkCodeFiles(List<Screen> screens) 
    throws CoderException {
        Project generatedProject = framework.generateCode(screens);
        
        return generatedProject.getCodeFiles();
    }

    private void doCodeExportation() throws CodeExportException {
        codeExport = new MobilangCodeExport
            .Builder()
            .properties(properties)
            .dependencies(dependencies)
            .codeFiles(codeFiles)
            .framework(framework)
            .output(output)
            .build();

        output = codeExport.export();
    }

    private void assertCodeWasExported() {
        Assertions.assertTrue(Files.exists(output));
    }
}
