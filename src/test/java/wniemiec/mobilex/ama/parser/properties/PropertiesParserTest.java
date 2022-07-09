package wniemiec.mobilex.ama.parser.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.paypal.digraph.parser.GraphNode;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.MobilangDotReader;


class PropertiesParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private MobilangDotReader dotReader;
    private PropertiesParser parser;
    private Node propertiesNode;
    private Properties obtainedProperties;


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
        dotReader = new MobilangDotReader();
        parser = null;
        propertiesNode = null;
        obtainedProperties = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParserWithApplicationNameAndPlatforms() throws ParseException, IOException {
        withPropertiesNode("n9", "<properties>");
        withAst("HelloWorld.dot");
        doParsing();
        assertApplicationName("HelloWorld");
        assertTargetPlatforms("android", "ios");
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withPropertiesNode(String id, String label) {
        propertiesNode = new Node(buildGraphNodeWithIdAndLabel(id, label));
    }

    private GraphNode buildGraphNodeWithIdAndLabel(String id, String label) {
        GraphNode node = new GraphNode(id);

        node.setAttribute("label", label);

        return node;
    }

    private void withAst(String file) throws FileNotFoundException {
        dotReader.read(RESOURCES.resolve(file));
    }

    private void doParsing() throws ParseException, IOException {
        parser = new PropertiesParser(dotReader.getTree(), propertiesNode);
        
        parser.parse();
        obtainedProperties = parser.getProperties();
    }

    private void assertApplicationName(String name) {
        Assertions.assertEquals(name, obtainedProperties.getApplicationName());
    }

    private void assertTargetPlatforms(String... platforms) {
        Set<String> obtainedPlatforms = obtainedProperties.getTargetPlatforms();

        for (String platform : platforms) {
            Assertions.assertTrue(obtainedPlatforms.contains(platform));
        }
        
    }    
}
