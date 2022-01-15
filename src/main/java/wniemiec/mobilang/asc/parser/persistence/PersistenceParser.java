package wniemiec.mobilang.asc.parser.persistence;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.data.Node;
import wniemiec.mobilang.asc.parser.Parser;

public class PersistenceParser implements Parser {

    private String persistenceContent;

    public PersistenceParser(SortedMap<String, List<Node>> tree, Node persistenceNode) {
        persistenceContent = tree.get(persistenceNode.getId()).get(0).getLabel();
    }

    @Override
    public void parse() {
        System.out.println("-----< PERSISTENCE PARSER >-----");
        System.out.println(persistenceContent);
        System.out.println("-------------------------------\n");
    }
}
