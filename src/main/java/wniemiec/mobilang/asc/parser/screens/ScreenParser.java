package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.asc.coder.framework.reactnative.ReactNativeFrameworkScreenCoder;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.StyleSheet;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.models.Variable;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.parser.framework.FrameworkScreenParser;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;
import wniemiec.mobilang.asc.parser.screens.behavior.BehaviorParser;
import wniemiec.mobilang.asc.parser.screens.structure.StructureParser;
import wniemiec.mobilang.asc.parser.screens.style.StyleParser;

public class ScreenParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private String id;
    private Node structureNode;
    private Node styleNode;
    private Node behaviorNode;
    private StructureParser structureParser;
    private StyleParser styleParser;
    private BehaviorParser behaviorParser;
    private FrameworkScreenParser frameworkParser;
    private FrameworkParserFactory frameworkParserFactory;
    private ScreenData screenData;

    public ScreenParser(SortedMap<String, List<Node>> tree, Node screenNode, FrameworkParserFactory frameworkParserFactory) {
        this.tree = tree;
        this.frameworkParserFactory = frameworkParserFactory;
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

        
        frameworkParser = frameworkParserFactory.getScreenParser(
            id,
            structure,
            style,
            behavior
        );
        
        frameworkParser.parse();

        this.screenData = frameworkParser.getScreenData();
        
        System.out.println("-------------------------------\n");
    }

   
    public ScreenData getScreenData() {
        return screenData;
    }

}
