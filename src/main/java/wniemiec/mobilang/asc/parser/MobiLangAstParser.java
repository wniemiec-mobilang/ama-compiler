package wniemiec.mobilang.asc.parser;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.PropertiesData;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.persistence.PersistenceData;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.persistence.PersistenceParser;
import wniemiec.mobilang.asc.parser.properties.PropertiesParser;
import wniemiec.mobilang.asc.parser.screens.ScreensParser;


/**
 * Responsible for parsing MobiLang AST.
 */
public class MobiLangAstParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private final FrameworkParserFactory frameworkParserFactory;
    private ScreensParser screensParser;
    private PropertiesParser propertiesParser;
    private PersistenceParser persistenceParser;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang AST parser.
     * 
     * @param       ast MobiLang AST
     * @param       frameworkParserFactory Factory that will provide framework 
     * parser
     */
    public MobiLangAstParser(
        SortedMap<String, List<Node>> ast, 
        FrameworkParserFactory frameworkParserFactory
    ) {
        this.ast = ast;
        this.frameworkParserFactory = frameworkParserFactory;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException, IOException {
        List<Node> root = getAstNode();
        
        parseScreensNode(root);
        parsePropertiesNode(root);
        parsePersistenceNode(root);
    }

    private List<Node> getAstNode() {
        return ast.get("n0");
    }

    private void parseScreensNode(List<Node> root) throws ParseException, IOException {
        screensParser = buildScreensParser(root);
        screensParser.parse();
    }

    private ScreensParser buildScreensParser(List<Node> root) {
        Node screensNode = findNodeWithName(root, "screens");
        
        return new ScreensParser(ast, screensNode, frameworkParserFactory);
    }

    private void parsePropertiesNode(List<Node> root) throws ParseException {
        propertiesParser = buildPropertiesParser(root);
        propertiesParser.parse();
    }

    private PropertiesParser buildPropertiesParser(List<Node> root) {
        Node propertiesNode = findNodeWithName(root, "properties");
        
        return new PropertiesParser(ast, propertiesNode);
    }

    private void parsePersistenceNode(List<Node> root) throws ParseException {
        persistenceParser = buildPersistenceParser(root);
        persistenceParser.parse();
    }

    private PersistenceParser buildPersistenceParser(List<Node> root) {
        Node persistenceNode = findNodeWithName(root, "persistence");
        
        return new PersistenceParser(ast, persistenceNode);
    }

    private Node findNodeWithName(List<Node> root, String name) {
        Node searchedNode = null;
        int index = 0;
        int totalElements = root.size();
        
        while ((searchedNode == null) && (index < totalElements)) {
            Node currentNode = root.get(index);
            
            if (currentNode.getLabel().contains(name)) {
                searchedNode = currentNode;
            }
            
            index++;
        }
        
        return searchedNode;
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<ScreenData> getScreensData() {
        return screensParser.getScreensData();
    }

    public PropertiesData getPropertiesData() {
        return propertiesParser.getPropertiesData();
    }

    public PersistenceData getPersistenceData() {
        return persistenceParser.getPersistenceData();
    }
}
