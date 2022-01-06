package wniemiec.mobilang.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.paypal.digraph.parser.GraphNode;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.persistence.PersistenceParser;
import wniemiec.mobilang.parser.properties.PropertiesParser;
import wniemiec.mobilang.parser.screens.ScreensParser;
import wniemiec.mobilang.reader.DotReader;

public class DotParser implements Parser {

    private Path dotFile;
    private Path outputLocation;
    private DotReader dotReader = new DotReader();
    private Parser screensParser;
    private Parser propertiesParser;
    private Parser persistenceParser;
    
    public DotParser(Path dotFile, Path outputLocation) {
        this.dotFile = dotFile;
        this.outputLocation = outputLocation;
    }

    public void parse() throws Exception {
        SortedMap<String, List<Node>> tree = dotReader.read(dotFile);

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
        
        screensParser = new ScreensParser(tree, screensNode);
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
}
