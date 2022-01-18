package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.asc.data.Node;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;
import wniemiec.mobilang.asc.parser.screens.behavior.BehaviorParser;
import wniemiec.mobilang.asc.parser.screens.structure.StructureParser;
import wniemiec.mobilang.asc.parser.screens.structure.Tag;
import wniemiec.mobilang.asc.parser.screens.style.StyleParser;
import wniemiec.mobilang.asc.parser.screens.style.StyleSheet;

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

        Tag structure = structureParser.parse();
        StyleSheet style = styleParser.parse();
        Behavior behavior = behaviorParser.parse();

        ReactNativeScreenParser rnParser = new ReactNativeScreenParser(
            structure,
            style,
            behavior
        );

        rnParser.parse();

        ReactNativeScreenCode rnCode = new ReactNativeScreenCode(
            id, 
            rnParser.getImports(), 
            rnParser.getDeclarations(),
            rnParser.getStateDeclarations(), 
            rnParser.getStateBody(), 
            rnParser.getBody()
        );

        List<String> code = rnCode.generateCode();

        System.out.println("\n\n----- CODE ----");
        for (String line : code) {
            System.out.println(line);
        }
        
        System.out.println("-------------------------------\n");
    }
}
