package wniemiec.mobilang.asc.framework.coder.reactnative;

import java.util.Collection;
import java.util.List;
import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.coder.FrameworkCoreCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.ScreenData;


/**
 * Provides coders of React Native framework.
 */
public class ReactNativeFrameworkCoderFactory implements FrameworkCoderFactory {

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
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
