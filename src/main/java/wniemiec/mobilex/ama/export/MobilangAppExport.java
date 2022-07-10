package wniemiec.mobilex.ama.export;

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
 * Responsible for Mobilang mobile application exportation.
 */
public class MobilangAppExport {

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
    private MobilangAppExport(
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
    //		Builder
    //-------------------------------------------------------------------------
    /**
     * Required fields:
     * 
     * - framework
     * - sourceCode
     * - output
     * - platforms
     */
    public static class Builder {

        private Framework framework;
        private Path sourceCode;
        private Path output;
        private Set<String> platforms;

        public Builder framework(Framework framework) {
            this.framework = framework;
            
            return this;
        }

        public Builder sourceCode(Path sourceCode) {
            this.sourceCode = sourceCode;
            
            return this;
        }

        public Builder output(Path output) {
            this.output = output;
            
            return this;
        }

        public Builder platforms(Set<String> platforms) {
            this.platforms = platforms;
            
            return this;
        }

        public MobilangAppExport build() {
            validateFields();

            return new MobilangAppExport(
                framework, 
                sourceCode, 
                output, 
                platforms
            );
        }

        private void validateFields() {
            validateFramework();
            validateSourceCode();
            validateOutput();
            validatePlatforms();
        }

        private void validateFramework() {
            if (framework == null) {
                throw new IllegalStateException("Framework cannot be null");
            }
        }

        private void validateSourceCode() {
            if (sourceCode == null) {
                throw new IllegalStateException("SourceCode cannot be null");
            }
        }

        private void validateOutput() {
            if (output == null) {
                throw new IllegalStateException("Output cannot be null");
            }
        }

        private void validatePlatforms() {
            if (platforms == null) {
                throw new IllegalStateException("Platforms cannot be null");
            }
        }
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
