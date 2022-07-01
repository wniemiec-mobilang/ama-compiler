package wniemiec.mobilex.ama.export.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import wniemiec.io.java.Consolex;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for MobiLang code exportation.
 */
public class MobiLangAppExport {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Framework framework;
    private final Path source;
    private final Path output;
    private final Set<String> platforms;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MobiLangAppExport(
        Framework framework, 
        Path sourceCode, 
        Path output, 
        Set<String> platforms
    ) {
        this.output = output.resolve("mobile");
        this.framework = framework;
        this.source = sourceCode;
        this.platforms = platforms;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public Path generateMobileApplications() throws AppGenerationException {
        setUpOutput();

        for (String platform : platforms) {
            generateMobileApplicationFor(platform);
        }
        
        return output;
    }

    private void setUpOutput() throws AppGenerationException {
        try {
            FileUtils.deleteDirectory(output.toFile());
            Files.createDirectories(output);
        } 
        catch (IOException e) {
            throw new AppGenerationException(e.toString());
        }
    }

    private void generateMobileApplicationFor(String platform) 
    throws AppGenerationException {
        Consolex.writeInfo("Generating " + capitalize(platform) + " app...");

        framework.generateMobileApplicationFor(platform, source, output);
    }

    private String capitalize(String platform) {
        return StringUtils.capitalize(platform);
    }
}
