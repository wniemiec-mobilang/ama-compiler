package wniemiec.mobilang.asc.framework.reactnative;

import wniemiec.mobilang.asc.framework.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.FrameworkFactory;
import wniemiec.mobilang.asc.framework.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.FrameworkProjectManagerFactory;

public class ReactNativeFrameworkFactory implements FrameworkFactory {

    @Override
    public FrameworkParserFactory getParserFactory() {
        return new ReactNativeFrameworkParserFactory();
    }

    @Override
    public FrameworkCoderFactory getCoderFactory() {
        return new ReactNativeFrameworkCoderFactory();
    }

    @Override
    public FrameworkProjectManagerFactory getProjectManagerFactory() {
        return new ReactNativeFrameworkProjectManagerFactory();
    }
    
}
