package wniemiec.mobilang.asc.coder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wniemiec.mobilang.asc.framework.FrameworkCoderFactory;
import wniemiec.mobilang.asc.framework.FrameworkCoreCoder;
import wniemiec.mobilang.asc.framework.FrameworkScreensCoder;
import wniemiec.mobilang.asc.models.FileCode;
import wniemiec.mobilang.asc.models.PersistenceData;
import wniemiec.mobilang.asc.models.ScreenData;

public class MobilangCoder {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private PersistenceData persistenceData;
    private List<ScreenData> screensData;
    private FrameworkCoderFactory frameworkCoderFactory;

    // key: filename; value: code
    private List<FileCode> screensCode;
    private List<FileCode> persistenceCode;
    private List<FileCode> coreCode;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MobilangCoder(
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
        FrameworkScreensCoder frameworkScreensCoder = frameworkCoderFactory.getScreensCoder(screensData);

        screensCode = frameworkScreensCoder.generateCode();
    }

    private void generateCodeForCore() {
        FrameworkCoreCoder frameworkCoreCoder = frameworkCoderFactory.getCoreCoder(extractScreensFilename());

        coreCode = frameworkCoreCoder.generateCode();        
    }


    private List<String> extractScreensFilename() {
        List<String> screensFilename = new ArrayList<>();

        for (FileCode fileCode : screensCode) {
            screensFilename.add(fileCode.getName());
        }

        return screensFilename;
    }

    private void generateCodeForPersistence() {
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
