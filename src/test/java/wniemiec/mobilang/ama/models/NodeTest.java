package wniemiec.mobilang.ama.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.paypal.digraph.parser.GraphNode;


class NodeTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private Node node;
    private String id;
    private String label;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        node = null;
        id = null;
        label = null;
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testGetAttributeWithoutAttributes() {
        withId("n1");
        withLabel("<foo>");
        buildNode();
        assertAttributeEquals("id", "");
    }

    @Test
    void testGetAttribute() {
        withId("n1");
        withLabel("<foo id=\"some-id\">");
        buildNode();
        assertAttributeEquals("id", "some-id");
    }

    @Test
    void testGetId() {
        withId("n1");
        buildNode();
        assertIdIs("n1");
    }

    @Test
    void testGetLabel() {
        withId("n1");
        withLabel("<foo>");
        buildNode();
        assertLabelIs("<foo>");
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withId(String nodeId) {
        id = nodeId;
    }

    private void withLabel(String nodeLabel) {
        label = nodeLabel;
    }

    private void buildNode() {
        node = new Node(buildGraphNode());
    }

    private GraphNode buildGraphNode() {
        GraphNode labeledNode = new GraphNode(id);

        labeledNode.setAttribute("label", label);

        return labeledNode;
    }

    private void assertAttributeEquals(String attribute, String value) {
        Assertions.assertEquals(value, node.getAttribute(attribute));
    }

    private void assertIdIs(String id) {
        Assertions.assertEquals(id, node.getId());
    }

    private void assertLabelIs(String label) {
        Assertions.assertEquals(label, node.getLabel());
    }
}
