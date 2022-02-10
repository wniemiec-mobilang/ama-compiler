package wniemiec.mobilang.asc.framework.coder;

import java.util.Collection;
import java.util.List;

import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.persistence.PersistenceData;


/**
 * Provides coders of a framework.
 */
public interface FrameworkCoderFactory {
    
    /**
     * Provides a framework code generator for screens.
     * 
     * @param       screenData Screens data
     * 
     * @return      Code generator
     */
    FrameworkScreensCoder getScreensCoder(List<ScreenData> screenData);

    /**
     * Provides a framework code generator for core files.
     * 
     * @param       screensName Screens name
     * 
     * @return      Code generator
     */
    FrameworkCoreCoder getCoreCoder(Collection<String> screensName);

    /**
     * Provides a framework code generator for persistence.
     * 
     * @param       persistenceData Persistence data
     * 
     * @return      Code generator
     */
    FrameworkPersistenceCoder getPersistenceCoder(PersistenceData persistenceData);
}
