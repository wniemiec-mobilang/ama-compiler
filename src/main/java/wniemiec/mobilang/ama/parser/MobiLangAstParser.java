package wniemiec.mobilang.ama.parser;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.ama.models.Node;
import wniemiec.mobilang.ama.models.Properties;
import wniemiec.mobilang.ama.models.Screen;
import wniemiec.mobilang.ama.parser.exception.ParseException;
import wniemiec.mobilang.ama.parser.properties.PropertiesParser;
import wniemiec.mobilang.ama.parser.screens.ScreensParser;


/**
 * Responsible for parsing MobiLang AST.
 */
public class MobiLangAstParser {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private ScreensParser screensParser;
    private PropertiesParser propertiesParser;
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang AST parser.
     * 
     * @param       ast MobiLang AST
     */
    public MobiLangAstParser(SortedMap<String, List<Node>> ast) {
        this.ast = ast;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException, IOException {
        List<Node> root = getAstNode();
        
        parseScreensNode(root);
        parsePropertiesNode(root);
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
        
        return new ScreensParser(ast, screensNode);
    }

    private void parsePropertiesNode(List<Node> root) throws ParseException {
        propertiesParser = buildPropertiesParser(root);
        propertiesParser.parse();
    }

    private PropertiesParser buildPropertiesParser(List<Node> root) {
        Node propertiesNode = findNodeWithName(root, "properties");
        
        return new PropertiesParser(ast, propertiesNode);
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
    public List<Screen> getScreensData() {
        return screensParser.getScreensData();
    }

    public Properties getPropertiesData() {
        return propertiesParser.getPropertiesData();
    }
}
