package wniemiec.mobilang.asc.coder;

import java.util.ArrayList;
import java.util.List;

import wniemiec.mobilang.asc.framework.coder.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.coder.FrameworkCoreCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkPersistenceCoder;
import wniemiec.mobilang.asc.framework.coder.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.ScreenData;
import wniemiec.mobilang.asc.models.persistence.PersistenceData;


/**
 * Responsible for MobiLang code generation.
 */
public class MobiLangCoder {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private PersistenceData persistenceData;
    private List<ScreenData> screensData;
    private FrameworkCoderFactory frameworkCoderFactory;
    private List<FileCode> screensCode;
    private List<FileCode> persistenceCode;
    private List<FileCode> coreCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang code generator.
     * 
     * @param       persistenceData Persistence data
     * @param       screensData Screens data
     * @param       frameworkCoderFactory Factory that will provide framework 
     * coder
     */
    public MobiLangCoder(
        PersistenceData persistenceData, 
        List<ScreenData> screensData,
        FrameworkCoderFactory frameworkCoderFactory
    ) {
        this.persistenceData = persistenceData;
        this.screensData = screensData;
        this.frameworkCoderFactory = frameworkCoderFactory;
        screensCode = new ArrayList<>();
        persistenceCode = new ArrayList<>();
        coreCode = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void generateCode() {
        generateCodeForScreens();
        generateCodeForCore();
        generateCodeForPersistence();
    }

    private void generateCodeForScreens() {
        FrameworkScreensCoder screensCoder = getScreensCoder();

        screensCode = screensCoder.generateCode();
    }

    private FrameworkScreensCoder getScreensCoder() {
        return frameworkCoderFactory.getScreensCoder(screensData);
    }

    private void generateCodeForCore() {
        FrameworkCoreCoder coreCoder = getCoreCoder();

        coreCode = coreCoder.generateCode();        
    }

    private FrameworkCoreCoder getCoreCoder() {
        List<String> screensFilename = extractScreensFilename();

        return frameworkCoderFactory.getCoreCoder(screensFilename);
    }

    private List<String> extractScreensFilename() {
        List<String> screensFilename = new ArrayList<>();

        for (FileCode fileCode : screensCode) {
            screensFilename.add(fileCode.getName());
        }

        return screensFilename;
    }

    private void generateCodeForPersistence() {
        FrameworkPersistenceCoder persistenceCoder = getPersistenceCoder();

        persistenceCode = persistenceCoder.generateCode();
    }

    private FrameworkPersistenceCoder getPersistenceCoder() {
        return frameworkCoderFactory.getPersistenceCoder(persistenceData);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<FileCode> getScreensCode() {
        return screensCode;
    }

    public List<FileCode> getPersistenceCode() {
        return persistenceCode;
    }

    public List<FileCode> getCoreCode() {
        return coreCode;
    }
}
