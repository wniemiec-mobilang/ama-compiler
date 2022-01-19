package wniemiec.mobilang.asc.reader;

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

import wniemiec.mobilang.asc.models.Node;

public class DotReader {

    public DotReader() {
    }
    
    public SortedMap<String, List<Node>> read(Path filepath) throws FileNotFoundException {
        SortedMap<String, List<Node>> tree = new TreeMap<>();
        GraphParser parser = new GraphParser(new FileInputStream(filepath.toFile()));
        Map<String, GraphEdge> edges = parser.getEdges();
        for (GraphEdge edge : edges.values()) {
            GraphNode nodeLeft = edge.getNode1();
            GraphNode nodeRight = edge.getNode2();
            String key = nodeLeft.getId();
            
            if (tree.containsKey(key)) {
                List<Node> children = tree.get(key);
                children.add(new Node(nodeRight));
            }
            else {
                List<Node> children = new ArrayList<>();
                children.add(new Node(nodeRight));
                tree.put(key, children);
            }
        }


        return tree;
    }

    private int extractNumberFromNode(GraphNode node) {
        return Integer.parseInt(node.getId().substring(1));
    }

    private String extractNodeLabel(GraphNode node) {
        return ((String) node.getAttribute("label"));
    }   
}
