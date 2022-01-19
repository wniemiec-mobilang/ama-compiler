package wniemiec.mobilang.asc.parser.framework.reactnative;

import wniemiec.mobilang.asc.models.StyleSheet;
import wniemiec.mobilang.asc.models.Tag;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.parser.framework.FrameworkScreenParser;
import wniemiec.mobilang.asc.parser.screens.behavior.Behavior;

public class ReactNativeFrameworkParserFactory implements FrameworkParserFactory {

    @Override
    public FrameworkScreenParser getScreenParser(String name, Tag structure, StyleSheet style, Behavior behavior) {
        return new ReactNativeFrameworkScreenParser(name, structure, style, behavior);
    }
    
}
