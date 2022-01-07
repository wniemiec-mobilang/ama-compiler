package wniemiec.mobilang.parser.screens;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.Parser;
import wniemiec.mobilang.parser.screens.behavior.BehaviorParser;
import wniemiec.mobilang.parser.screens.structure.StructureParser;
import wniemiec.mobilang.parser.screens.style.StyleParser;

public class ScreenParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private String id;
    private Node structureNode;
    private Node styleNode;
    private Node behaviorNode;
    private StructureParser structureParser;
    private StyleParser styleParser;
    private BehaviorParser behaviorParser;

    public ScreenParser(SortedMap<String, List<Node>> tree, Node screenNode) {
        this.tree = tree;
        id = screenNode.getAttribute("id");
        
        for (Node node : tree.get(screenNode.getId())) {
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
        
        System.out.println("Screen id: " + id);
        
        structureParser = new StructureParser(tree, structureNode);
        styleParser = new StyleParser(tree, styleNode);
        behaviorParser = new BehaviorParser(tree, behaviorNode);

        //structureParser.parse();
        styleParser.parse();
        //behaviorParser.parse();
        
        System.out.println("-------------------------------\n");
    }
}
