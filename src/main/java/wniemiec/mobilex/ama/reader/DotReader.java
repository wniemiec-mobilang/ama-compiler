package wniemiec.mobilex.ama.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;

import wniemiec.mobilex.ama.models.Node;


/**
 * Responsible for reading dot files.
 */
public class DotReader {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> tree;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public DotReader() {
        tree = new TreeMap<>();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void read(Path filepath) throws FileNotFoundException {
        Map<String, GraphEdge> edges = readEdges(filepath);
        
        for (GraphEdge edge : edges.values()) {
            parseEdge(edge);
        }
    }

    private void parseEdge(GraphEdge edge) {
        GraphNode nodeLeft = edge.getNode1();
        GraphNode nodeRight = edge.getNode2();
        String key = nodeLeft.getId();
        
        if (tree.containsKey(key)) {
            addChildToNode(nodeRight, key);
        }
        else {
            addNode(nodeRight, key);
        }
    }

    private void addChildToNode(GraphNode child, String nodeId) {
        List<Node> children = tree.get(nodeId);

        children.add(new Node(child));
    }

    private void addNode(GraphNode nodeRight, String nodeId) {
        List<Node> children = new ArrayList<>();
        
        children.add(new Node(nodeRight));
        tree.put(nodeId, children);
    }

    private Map<String, GraphEdge> readEdges(Path filepath) 
    throws FileNotFoundException {
        GraphParser parser = new GraphParser(buildInputStream(filepath));
        
        return parser.getEdges();
    }

    private FileInputStream buildInputStream(Path filepath) 
    throws FileNotFoundException {
        return new FileInputStream(filepath.toFile());
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public SortedMap<String, List<Node>> getTree() {
        return tree;
    }
}
