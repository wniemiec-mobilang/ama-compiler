package wniemiec.mobilang.asc.framework.coder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.ScreenData;

public interface FrameworkCoderFactory {
    
    FrameworkScreensCoder getScreensCoder(List<ScreenData> screenData);
    FrameworkCoreCoder getCoreCoder(Collection<String> screensName);
    FrameworkPersistenceCoder getPersistenceCoder(PersistenceData persistenceData);
}
