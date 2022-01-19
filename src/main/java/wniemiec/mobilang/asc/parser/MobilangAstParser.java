package wniemiec.mobilang.asc.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.parser.persistence.PersistenceParser;
import wniemiec.mobilang.asc.parser.properties.PropertiesParser;
import wniemiec.mobilang.asc.parser.screens.ScreensParser;
import wniemiec.mobilang.asc.reader.DotReader;

public class MobilangAstParser implements Parser {
    
    private ScreensParser screensParser;
    private Parser propertiesParser;
    private Parser persistenceParser;
    private SortedMap<String, List<Node>> tree;
    private FrameworkParserFactory frameworkParserFactory;
    
    public MobilangAstParser(SortedMap<String, List<Node>> tree, FrameworkParserFactory frameworkParserFactory) {
        this.tree = tree;
        this.frameworkParserFactory = frameworkParserFactory;
    }

    public void parse() throws ParseException {
        

        /*for (String line : Files.readAllLines(dotFile)) {
            System.out.println(line);
        }*/
        /*System.out.println("\n");

        for (Map.Entry<String, List<Node>> element : tree.entrySet()) {
            System.out.println(element.getKey() + " -> " + element.getValue());
        }

        System.out.println("\n");*/

        //System.out.println(tree);

        
        List<Node> root = tree.get("n0");
        Node screensNode = findNodeWithName(root, "screens");
        Node propertiesNode = findNodeWithName(root, "properties");
        Node persistenceNode = findNodeWithName(root, "persistence");
        
        screensParser = new ScreensParser(tree, screensNode, frameworkParserFactory);
        propertiesParser = new PropertiesParser(tree, propertiesNode);
        persistenceParser = new PersistenceParser(tree, persistenceNode);

        screensParser.parse();
        //propertiesParser.parse();
        //persistenceParser.parse();
    }

    private Node findNodeWithName(List<Node> root, String name) {
        Node searchedNode = null;
        int index = 0;
        int totalElements = root.size();
        
        while (searchedNode == null && index < totalElements) {
            Node currentNode = root.get(index);
            //String nodeLabel = extractLabelFromNode(currentNode);
            
            if (currentNode.getLabel().contains(name))
                searchedNode = currentNode;
            
            index++;
        }
        
        return searchedNode;
    }

    public List<ScreenData> getScreensData() {
        return screensParser.getScreensData();
    }
}
