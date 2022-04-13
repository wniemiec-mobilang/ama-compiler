package wniemiec.mobilang.asc.coder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wniemiec.mobilang.asc.coder.exception.CoderException;
import wniemiec.mobilang.asc.framework.Framework;
import wniemiec.mobilang.asc.models.CodeFile;
import wniemiec.mobilang.asc.models.ProjectCodes;
import wniemiec.mobilang.asc.models.ScreenData;


/**
 * Responsible for MobiLang code generation.
 */
public class MobiLangCoder {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<ScreenData> screensData;
    private final Framework framework;
    private final List<CodeFile> codeFiles;
    private final Set<String> dependencies;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * MobiLang code generator.
     * 
     * @param       persistenceData Persistence data
     * @param       screensData Screens data
     * @param       framework Framework that will handle with code generation
     */
    public MobiLangCoder(
        List<ScreenData> screensData,
        Framework framework
    ) {
        this.screensData = screensData;
        this.framework = framework;
        this.codeFiles = new ArrayList<>();
        dependencies = new HashSet<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void generateCode() throws CoderException {
        ProjectCodes codes = framework.generateCode(screensData);

        codeFiles.addAll(codes.getCodeFiles());
        dependencies.addAll(codes.getDependencies());
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }
}
