package wniemiec.mobilex.ama.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class CliParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private CliParser parser;
    private List<String> args;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        parser = new CliParser();
        args = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParserWithAstAndOutputAndFramework() throws ParseException {
        withArg("-ast");
        withArg("./hello-world.mobilang.xml");
        withArg("-output");
        withArg("./examples");
        withArg("-framework");
        withArg("react-native");
        doParsing();
        assertAstFileIs("./hello-world.mobilang.xml");
        assertOutputFileIs("./examples");
        assertFrameworkIs("react-native");
    }

    @Test
    void testParserWithOutputAndFramework() throws ParseException {
        withArg("-output");
        withArg("./examples");
        withArg("-framework");
        withArg("react-native");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }

    @Test
    void testParserWithOutput() throws ParseException {
        withArg("-output");
        withArg("./examples");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }

    @Test
    void testParserWithFramework() throws ParseException {
        withArg("-framework");
        withArg("react-native");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            doParsing();
        });
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withArg(String argument) {
        args.add(argument);
    }

    private void doParsing() throws ParseException {
        parser.parse(args.toArray(new String[] {}));
    }

    private void assertAstFileIs(String file) {
        assertPathsAreEqual(Path.of(file), parser.getMobilangAstFilePath());
    }

    private void assertPathsAreEqual(Path p1, Path p2) {
        Assertions.assertEquals(
            p1.normalize().toAbsolutePath(), 
            p2.normalize().toAbsolutePath()
        );
    }

    private void assertOutputFileIs(String file) {
        assertPathsAreEqual(Path.of(file), parser.getOutputLocationPath());
    }

    private void assertFrameworkIs(String name) {
        Assertions.assertEquals(name, parser.getFrameworkName());
    }
}
