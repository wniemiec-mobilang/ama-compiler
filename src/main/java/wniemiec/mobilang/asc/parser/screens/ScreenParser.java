package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import wniemiec.mobilang.asc.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.FrameworkScreenParser;
import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.screens.behavior.BehaviorParser;
import wniemiec.mobilang.asc.parser.screens.structure.StructureParser;
import wniemiec.mobilang.asc.parser.screens.style.StyleParser;
import wniemiec.mobilang.asc.utils.StringUtils;


/**
 * Responsible for parsing screen node from MobiLang AST.
 */
public class ScreenParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private SortedMap<String, List<Node>> ast;
    private String id;
    private Node structureNode;
    private Node styleNode;
    private Node behaviorNode;
    private FrameworkScreenParser frameworkParser;
    private FrameworkParserFactory frameworkParserFactory;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screen parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       screenNode Screen node
     * @param       frameworkParserFactory Factory that will provide framework 
     * parser
     */
    public ScreenParser(
        SortedMap<String, List<Node>> ast, 
        Node screenNode, 
        FrameworkParserFactory frameworkParserFactory
    ) {
        this.ast = ast;
        this.frameworkParserFactory = frameworkParserFactory;
        id = buildScreenId(screenNode);

        initializeStructureStyleAndBehaviorNodes(screenNode);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private String buildScreenId(Node screenNode) {
        String screenId = screenNode.getAttribute("id");
        
        screenId = normalize(screenId) + "Screen";

        return screenId;
    }

    private String normalize(String str) {
        String normalizedString;

        normalizedString = str.replaceAll("[\\-\\_]+", "");
        normalizedString = StringUtils.capitalize(normalizedString);

        return normalizedString;
    }

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
    
    public void parse() throws ParseException, IOException {
        Tag structure = parseStructureNode();
        Style style = parseStyleNode();
        Behavior behavior = parseBehaviorNode();
        
        parseScreen(structure, style, behavior);
    }

    private Tag parseStructureNode() throws ParseException {
        StructureParser structureParser = new StructureParser(ast, structureNode);
        
        return structureParser.parse();
    }

    private Style parseStyleNode() throws ParseException {
        StyleParser styleParser = new StyleParser(ast, styleNode);
        
        return styleParser.parse();
    }

    private Behavior parseBehaviorNode() throws ParseException {
        BehaviorParser behaviorParser = new BehaviorParser(ast, behaviorNode);
        
        return behaviorParser.parse();
    }

    private void parseScreen(Tag structure, Style style, Behavior behavior) 
    throws ParseException, IOException {
        frameworkParser = frameworkParserFactory.getScreenParser(
            id,
            structure,
            style,
            behavior
        );
        
        frameworkParser.parse();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public ScreenData getScreenData() {
        return frameworkParser.getScreenData();
    }

}
