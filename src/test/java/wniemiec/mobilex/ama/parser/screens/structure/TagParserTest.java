package wniemiec.mobilex.ama.parser.screens.structure;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.paypal.digraph.parser.GraphNode;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.reader.DotReader;


class TagParserTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final Path RESOURCES;
    private DotReader dotReader;
    private TagParser parser;
    private Node tagNode;
    private Tag parsedTag;


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
        parser = new TagParser();
        tagNode = null;
        parsedTag = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testParseOneScreenWithStructureAndStyleAndBehavior() 
    throws ParseException, IOException {
        withTagNode("n3", "<structure>");
        withAst("HelloWorld.dot");
        doParsing();
        assertParsedTagIs(
            "<html>",
            "<h1>",
            "Hello",
            "</h1>",
            "<h2>",
            "World!",
            "</h2>",
            "</html>"
        );
    }

    
    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withTagNode(String id, String label) {
        tagNode = new Node(buildGraphNodeWithIdAndLabel(id, label));
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
        String tagContent = getLabelFromAst(tagNode.getId());
        JSONObject jsonBodyTag = generateHtmlTag(tagContent);
        
        parsedTag = parser.parseBodyTag(jsonBodyTag);
    }
    
    private String getLabelFromAst(String nodeId) {
        return dotReader.getTree().get(nodeId).get(0).getLabel();
    }

    private JSONObject generateHtmlTag(String json) {
        JSONObject htmlTag = new JSONObject();
        JSONObject content = parseJsonContent(json);
        
        content.put("name", "html");
        htmlTag.put("nodeType", "tag");
        htmlTag.put("content", content);

        return htmlTag;
    }

    private JSONObject parseJsonContent(String json) {
        JSONObject jsonObject = new JSONObject(json);

        return jsonObject.getJSONObject("content");
    }

    private void assertParsedTagIs(String... code) {
        assertCode(parsedTag.toCode(), code);
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
}
