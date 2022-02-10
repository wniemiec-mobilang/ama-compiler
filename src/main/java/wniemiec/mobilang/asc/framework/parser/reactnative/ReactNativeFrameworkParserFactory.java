package wniemiec.mobilang.asc.framework.parser.reactnative;

import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.parser.FrameworkScreenParser;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.behavior.Behavior;
import wniemiec.mobilang.asc.models.tag.Tag;


/**
 * Provides parsers of React Native framework.
 */
public class ReactNativeFrameworkParserFactory implements FrameworkParserFactory {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public FrameworkScreenParser getScreenParser(
        String name, 
        Tag structure, 
        Style style, 
        Behavior behavior
    ) {
        return new ReactNativeFrameworkScreenParser(name, structure, style, behavior);
    }
    
}
