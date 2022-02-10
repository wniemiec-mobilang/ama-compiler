package wniemiec.mobilang.asc.parser.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;
import wniemiec.mobilang.asc.models.Node;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.parser.exception.ParseException;


/**
 * Responsible for parsing screens node from MobiLang AST.
 */
public class ScreensParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final SortedMap<String, List<Node>> ast;
    private final List<Node> screens;
    private final FrameworkParserFactory frameworkParserFactory;
    private final List<ScreenData> screensData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Screens parser for MobiLang AST.
     * 
     * @param       ast MobiLang AST
     * @param       screensNode Screens node
     * @param       frameworkParserFactory Factory that will provide framework 
     * parser
     */
    public ScreensParser(
        SortedMap<String, List<Node>> ast, 
        Node screensNode, 
        FrameworkParserFactory frameworkParserFactory
    ) {
        this.ast = ast;
        screens = ast.get(screensNode.getId());
        this.frameworkParserFactory = frameworkParserFactory;
        screensData = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse() throws ParseException, IOException {
        for (Node screen : screens) {
            ScreenParser screenParser = new ScreenParser(
                ast, 
                screen,
                frameworkParserFactory
            );
            
            screenParser.parse();
            screensData.add(screenParser.getScreenData());
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<ScreenData> getScreensData() {
        return screensData;
    }
}
