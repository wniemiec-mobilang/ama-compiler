package wniemiec.mobilang.asc.coder.framework.reactnative;

import wniemiec.mobilang.asc.coder.framework.FrameworkCoderFactory;
import wniemiec.mobilang.asc.coder.framework.FrameworkScreenCoder;
import wniemiec.mobilang.asc.models.ScreenData;

public class ReactNativeFrameworkCoderFactory implements FrameworkCoderFactory {

    @Override
    public FrameworkScreenCoder getScreenCoder(ScreenData screenData) {
        return new ReactNativeFrameworkScreenCoder(screenData);
    }
}
