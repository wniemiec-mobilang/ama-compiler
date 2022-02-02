package wniemiec.mobilang.asc.framework.reactnative;

import wniemiec.mobilang.asc.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.FrameworkScreenParser;
import wniemiec.mobilang.asc.models.Behavior;
import wniemiec.mobilang.asc.models.Style;
import wniemiec.mobilang.asc.models.Tag;

public class ReactNativeFrameworkParserFactory implements FrameworkParserFactory {

    @Override
    public FrameworkScreenParser getScreenParser(String name, Tag structure, Style style, Behavior behavior) {
        return new ReactNativeFrameworkScreenParser(name, structure, style, behavior);
    }
    
}
