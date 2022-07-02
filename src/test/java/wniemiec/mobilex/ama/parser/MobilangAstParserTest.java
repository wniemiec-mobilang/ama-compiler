package wniemiec.mobilex.ama.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.paypal.digraph.parser.GraphNode;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.DotReader;


class MobilangAstParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private DotReader dotReader;
    private MobilangAstParser parser;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        RESOURCES = Path.of(".", "src", "test", "resources");
    }


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        dotReader = new DotReader();
        parser = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithNodeAndTwoChildren() throws ParseException, IOException {
        withAst("HelloWorld.dot");
        doParsing();
        assertHasScreens("Home");
        assertScreenStructure(
            "Home",
            "<html>",
            "<h1>",
            "Hello",
            "</h1>",
            "<h2>",
            "World!",
            "</h2>",
            "</html>"
        );
        assertScreenStyle(
            "Home",
            "h1 {",
            "color: red;",
            "}"
        );
        assertScreenBehavior(
            "Home",
            "alert(\"World\");"
        );
        assertApplicationName("HelloWorld");
        assertPlatformsProperty("android", "ios");
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

    private void assertHasScreens(String... names) {
        List<String> screens = extractScreenNames(parser.getScreens());

        Arrays.asList(names).forEach(name -> {
            Assertions.assertTrue(screens.contains(name));
        });
    }

    private List<String> extractScreenNames(List<Screen> screens) {
        return screens
            .stream()
            .map(screen -> screen.getName())
            .collect(Collectors.toList());
    }

    private void assertScreenStructure(String name, String... code) {
        Screen screen = extractScreenWithName(name, parser.getScreens());

        assertStructureCodeIs(screen, code);
    }

    private Screen extractScreenWithName(String name, List<Screen> screens) {
        return screens
            .stream()
            .filter(screen -> screen.getName().equals(name))
            .findFirst()
            .get();
    }

    private void assertStructureCodeIs(Screen screen, String... code) {
        assertCode(screen.getStructure().toCode(), code);
    }

    private void assertCode(List<String> obtainedCode, String... expectedCode) {
        List<String> code = Arrays.asList(expectedCode);

        assertHasSameSize(code, obtainedCode);
        assertHasSameLines(code, obtainedCode);
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
    }

    private void assertHasSameLine(String expected, String obtained) {
        Assertions.assertEquals(
            removeWhiteSpaces(expected),
            removeWhiteSpaces(obtained)
        );
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }

    private void assertScreenStyle(String name, String... code) {
        Screen screen = extractScreenWithName(name, parser.getScreens());

        assertStyleCodeIs(screen, code);
    }

    private void assertStyleCodeIs(Screen screen, String... code) {
        assertCode(screen.getStyle().toCode(), code);
    }
    
    private void assertScreenBehavior(String name, String... code) {
        Screen screen = extractScreenWithName(name, parser.getScreens());

        assertBehaviorCodeIs(screen, code);
    }

    private void assertBehaviorCodeIs(Screen screen, String... code) {
        assertCode(screen.getBehavior().toCode(), code);
    }

    private void assertApplicationName(String name) {
        Assertions.assertEquals(name, parser.getProperties().getAppName());
    }

    private void assertPlatformsProperty(String... platforms) {
        Set<String> obtainedPlatforms = parser.getProperties().getPlatforms();

        for (String platform : platforms) {
            Assertions.assertTrue(obtainedPlatforms.contains(platform));
        }
        
    }


    
}
