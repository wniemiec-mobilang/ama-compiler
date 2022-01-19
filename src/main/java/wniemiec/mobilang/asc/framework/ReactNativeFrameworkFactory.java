package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.coder.framework.FrameworkCoderFactory;
import wniemiec.mobilang.asc.coder.framework.reactnative.ReactNativeFrameworkCoderFactory;
import wniemiec.mobilang.asc.parser.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.parser.framework.reactnative.ReactNativeFrameworkParserFactory;

public class ReactNativeFrameworkFactory implements FrameworkFactory {

    @Override
    public FrameworkParserFactory getParserFactory() {
        return new ReactNativeFrameworkParserFactory();
    }

    @Override
    public FrameworkCoderFactory getCoderFactory() {
        return new ReactNativeFrameworkCoderFactory();
    }
    
}
