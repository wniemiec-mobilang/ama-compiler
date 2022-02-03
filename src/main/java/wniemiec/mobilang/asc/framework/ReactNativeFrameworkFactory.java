package wniemiec.mobilang.asc.framework;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.coder.reactnative.ReactNativeFrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.manager.FrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.framework.manager.reactnative.ReactNativeFrameworkProjectManagerFactory;
import wniemiec.mobilang.asc.framework.parser.FrameworkParserFactory;
import wniemiec.mobilang.asc.framework.parser.reactnative.ReactNativeFrameworkParserFactory;


/**
 * Provides factories of React Native framework.
 */
public class ReactNativeFrameworkFactory implements FrameworkFactory {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
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
