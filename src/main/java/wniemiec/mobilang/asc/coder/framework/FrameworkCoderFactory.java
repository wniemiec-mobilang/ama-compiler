package wniemiec.mobilang.asc.coder.framework;

import java.util.Set;

import wniemiec.mobilang.asc.models.ScreenData;

public interface FrameworkCoderFactory {
    
    FrameworkScreenCoder getScreenCoder(ScreenData screenData);
    FrameworkCoreCoder getCoreCoder(Set<String> screensName);
}
