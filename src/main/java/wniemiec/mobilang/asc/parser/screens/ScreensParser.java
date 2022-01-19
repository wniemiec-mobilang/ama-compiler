package wniemiec.mobilang.asc.parser.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.coder.framework.reactnative.ReactNativeFrameworkScreenCoder;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.parser.Parser;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;

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
    public void parse() throws ParseException {
        /*for (Node screen : screens) {
            ScreenParser screenParser = new ScreenParser(tree, screen);
            
            screenParser.parse();
        }*/

        ScreenParser screenParser = new ScreenParser(
            tree, 
            screens.get(1),
            frameworkParserFactory
        );
            
        screenParser.parse();

        screensData.add(screenParser.getScreenData());

        /*
        ReactNativeScreenCode rnCode = new ReactNativeScreenCode( // frameworkCoderFactory.getScreenCoder() {mover para App.java}
            screenParser.getScreenData()
        );

        List<String> code = rnCode.generateCode();

        System.out.println("\n\n----- CODE ----");
        for (String line : code) {
            System.out.println(line);
        }
        */

        // frameworkParser = frameworkParserFactory.getScreensParser();
    }

    public List<ScreenData> getScreensData() {
        return screensData;
    }
}
