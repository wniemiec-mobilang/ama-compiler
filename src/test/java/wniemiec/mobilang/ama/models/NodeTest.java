package wniemiec.mobilang.ama.models;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.paypal.digraph.parser.GraphNode;


class NodeTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Node node;
    private GraphNode graphNode;
    private Map<String, String> attributes;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        node = null;
        graphNode = null;
        attributes = new HashMap<>();
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetAttributeWithoutAttributes() {
        withGraphNode(buildGraphNodeWithLabel("<foo>"));
        buildNode();
        assertIdAttributeIs("");
    }

    @Test
    void testGetAttribute() {
        withGraphNode(buildGraphNodeWithLabel("<screen id=\"stays\">"));
        buildNode();
        assertIdAttributeIs("stays");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private GraphNode buildGraphNodeWithLabel(String value) {
        GraphNode labeledNode = new GraphNode("n1");

        labeledNode.setAttribute("label", value);

        return labeledNode;
    }

    private void withGraphNode(GraphNode graphNode) {
        this.graphNode = graphNode;
    }

    private void buildNode() {
        node = new Node(graphNode);
    }

    private void assertIdAttributeIs(String value) {
        Assertions.assertEquals(value, node.getAttribute("id"));
    }
}
