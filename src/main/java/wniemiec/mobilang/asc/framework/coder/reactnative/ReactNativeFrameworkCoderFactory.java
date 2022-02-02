package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.coder.FrameworkCoreCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.ScreenData;

public class ReactNativeFrameworkCoderFactory implements FrameworkCoderFactory {

    @Override
    public FrameworkScreensCoder getScreensCoder(List<ScreenData> screensData) {
        return new ReactNativeFrameworkScreensCoder(screensData);
    }

    @Override
    public FrameworkCoreCoder getCoreCoder(Collection<String> screensName) {
        return new ReactNativeFrameworkCoreCoder(screensName);
    }

    @Override
    public FrameworkPersistenceCoder getPersistenceCoder(PersistenceData persistenceData) {
        return new ReactNativeFrameworkPersistenceCoder(persistenceData);
    }
}
