package wniemiec.mobilex.ama.parser.screens;

import java.util.List;
import java.util.SortedMap;
import wniemiec.mobilex.ama.models.Node;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.models.Style;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.tag.Tag;
import wniemiec.mobilex.ama.parser.exception.ParseException;
import wniemiec.mobilex.ama.parser.screens.behavior.BehaviorParser;
import wniemiec.mobilex.ama.parser.screens.structure.StructureParser;
import wniemiec.mobilex.ama.parser.screens.style.StyleParser;


/**
 * Responsible for parsing screen node from MobiLang AST.
 */
public class ScreenParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private final String id;
    private Node structureNode;
    private Node styleNode;
    private Node behaviorNode;
    private Screen screen;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screen parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       screenNode Screen node
     */
    public ScreenParser(SortedMap<String, List<Node>> ast, Node screenNode) {
        this.ast = ast;
        id = screenNode.getAttribute("id");

        initializeStructureStyleAndBehaviorNodes(screenNode);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void initializeStructureStyleAndBehaviorNodes(Node screenNode) {
        for (Node node : ast.get(screenNode.getId())) {
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
    
    public void parse() throws ParseException {
        Tag structure = parseStructureNode();
        Style style = parseStyleNode();
        Behavior behavior = parseBehaviorNode();
        
        parseScreen(structure, style, behavior);
    }

    private Tag parseStructureNode() throws ParseException {
        StructureParser structureParser = new StructureParser(ast, structureNode);
        
        return structureParser.parse();
    }

    private Style parseStyleNode() {
        StyleParser styleParser = new StyleParser(ast, styleNode);
        
        return styleParser.parse();
    }

    private Behavior parseBehaviorNode() throws ParseException {
        BehaviorParser behaviorParser = new BehaviorParser(ast, behaviorNode);
        
        return behaviorParser.parse();
    }

    private void parseScreen(Tag structure, Style style, Behavior behavior) {
        screen = new Screen.Builder()
            .name(id) 
            .structure(structure)
            .style(style)
            .behavior(behavior)
            .build();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Screen getScreen() {
        return screen;
    }
}
