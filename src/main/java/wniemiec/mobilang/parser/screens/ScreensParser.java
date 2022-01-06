package wniemiec.mobilang.parser.screens;

import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.data.Node;
import wniemiec.mobilang.parser.Parser;

public class ScreensParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private List<Node> screens;

    public ScreensParser(SortedMap<String, List<Node>> tree, Node screensNode) {
        this.tree = tree;
        screens = tree.get(screensNode.getId());
    }

    @Override
    public void parse() throws Exception {
        /*for (Node screen : screens) {
            ScreenParser screenParser = new ScreenParser(tree, screen);
            
            screenParser.parse();
        }*/

        ScreenParser screenParser = new ScreenParser(tree, screens.get(0));
            
        screenParser.parse();
    }
}
