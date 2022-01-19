package wniemiec.mobilang.asc.coder.framework;

import wniemiec.mobilang.asc.models.ScreenData;

public interface FrameworkCoderFactory {
    
    FrameworkScreenCoder getScreenCoder(ScreenData screenData);
}
