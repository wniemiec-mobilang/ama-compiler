package wniemiec.mobilang.parser;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.data.Node;

public class ScreenParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private String id;
    private Node structureNode;
    private Node styleNode;
    private Node behaviorNode;

    public ScreenParser(SortedMap<String, List<Node>> tree, Node screenNode) {
        this.tree = tree;
        id = screenNode.getAttribute("id");
        
        for (Node node : tree.get(screenNode.getLabel())) {
            if (node.getLabel().contains("structure")) {
                structureNode = node;
            }
            else if (node.getLabel().contains("style")) {
                styleNode = node;
            }
            else if (node.getLabel().contains("behavior")) {
                behaviorNode = node;
            }
        }
    }

    @Override
    public void parse() throws Exception {
        System.out.println("-----< SCREEN PARSER >-----");
        System.out.println(id);
        System.out.println(structureNode);
        System.out.println(styleNode);
        System.out.println(behaviorNode);
        System.out.println("-------------------------------\n");
    }
}
