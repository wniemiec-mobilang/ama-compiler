package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.reactnative.ReactNativeFrameworkScreensCoder;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.exception.ParseException;

public class ScreensParser implements Parser {

    private SortedMap<String, List<Node>> tree;
    private List<Node> screens;
    private FrameworkParserFactory frameworkParserFactory;
    private List<ScreenData> screensData;

    public ScreensParser(SortedMap<String, List<Node>> tree, Node screensNode, FrameworkParserFactory frameworkParserFactory) {
        this.tree = tree;
        screens = tree.get(screensNode.getId());
        this.frameworkParserFactory = frameworkParserFactory;
        screensData = new ArrayList<>();
    }

    @Override
    public void parse() throws ParseException, IOException {
        for (Node screen : screens) {
            ScreenParser screenParser = new ScreenParser(
                tree, 
                screen,
                frameworkParserFactory
            );
            
            screenParser.parse();
            screensData.add(screenParser.getScreenData());
        }

        /*
        ScreenParser screenParser = new ScreenParser(
            tree, 
            screens.get(1),
            frameworkParserFactory
        );
            
        screenParser.parse();

        screensData.add(screenParser.getScreenData());
        */
    }

    public List<ScreenData> getScreensData() {
        return screensData;
    }
}
