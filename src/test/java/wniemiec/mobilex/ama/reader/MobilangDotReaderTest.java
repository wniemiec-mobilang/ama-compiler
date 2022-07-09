package wniemiec.mobilex.ama.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.paypal.digraph.parser.GraphNode;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.parser.exception.ParseException;


class MobilangDotReaderTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private Path mobilangDotFile;
    private MobilangDotReader dotReader;
    private SortedMap<String, List<Node>> obtainedAst;
    private Ini iniReader;


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
        mobilangDotFile = null;
        obtainedAst = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseWithNodeAndTwoChildren() throws ParseException, IOException {
        withFile("HelloWorld.dot");
        withIniFile("HelloWorld.ini");
        doReading();
        assertNodeChildren(
            "n0", 
            buildNode("n1", "<screens>"), 
            buildNode("n9", "<properties>")
        );
        assertNodeChildren("n1", buildNode("n2", "<screen id=\"home\">"));
        assertNodeChildren(
            "n2", 
            buildNode("n3", "<structure>"), 
            buildNode("n5", "<style>"), 
            buildNode("n7","<behavior>")
        );
        assertNodeChildren("n3", buildNode("n4", getIniProperty("structure")));
        assertNodeChildren("n5", buildNode("n6", getIniProperty("style")));
        assertNodeChildren("n7", buildNode("n8", getIniProperty("behavior")));
        assertNodeChildren("n9", buildNode("n10", getIniProperty("properties")));
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withFile(String file) throws FileNotFoundException {
        mobilangDotFile = RESOURCES.resolve(file);
    }

    private void withIniFile(String file) throws InvalidFileFormatException, IOException {
        iniReader = new Ini(RESOURCES.resolve(file).toFile());
    }

    private void doReading() throws ParseException, IOException {
        dotReader.read(mobilangDotFile);
        obtainedAst = dotReader.getTree();
    }

    private Node buildNode(String id, String label) {
        return new Node(buildGraphNodeWithIdAndLabel(id, label));
    }

    private GraphNode buildGraphNodeWithIdAndLabel(String id, String label) {
        GraphNode node = new GraphNode(id);

        node.setAttribute("label", label);

        return node;
    }

    private void assertNodeChildren(String parent, Node... children) {
        assertAstHasNode(parent);
        assertAstNodeChildrenAre(parent, children);
    }

    private void assertAstHasNode(String parent) {
        Assertions.assertTrue(obtainedAst.containsKey(parent));
    }

    private void assertAstNodeChildrenAre(String parent, Node... children) {
        List<Node> nodeChildren = obtainedAst.get(parent);

        Arrays.asList(children).forEach(child -> {
            Node node = findNodeWithId(child.getId(), nodeChildren);

            Assertions.assertNotNull(node);
            Assertions.assertEquals(child.getLabel(), node.getLabel());
        });
    }

    private Node findNodeWithId(String id, List<Node> nodes) {
        return nodes
            .stream()
            .filter(node -> node.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    private String getIniProperty(String key) {
        return iniReader.get("ast", key);
    }
}
