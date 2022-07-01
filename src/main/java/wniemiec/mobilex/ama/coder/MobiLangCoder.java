package wniemiec.mobilex.ama.coder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Project;
import wniemiec.mobilex.ama.models.Screen;


/**
 * Responsible for MobiLang code generation.
 */
public class MobiLangCoder {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<Screen> screensData;
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
        List<Screen> screensData,
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
        Project codes = framework.generateCode(screensData);

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
