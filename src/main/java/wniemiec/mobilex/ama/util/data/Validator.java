package wniemiec.mobilex.ama.util.data;

import java.nio.file.Path;
import java.util.List;

import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.framework.Framework;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.util.io.FileManager;


public class Validator {
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private Validator() {
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public static void validateDependency(String dependency) {
        if (dependency == null) {
            throw new IllegalArgumentException("Dependency cannot be null");
        }
    }

    public static void validateProperties(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Properties cannot be null");
        }
    }

    public static void validateLocation(Path location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
    }

    public static void validateTerminal(Terminal terminal) {
        if (terminal == null) {
            throw new IllegalArgumentException("Terminal cannot be null");
        }
    }

    public static void validateFileManager(FileManager fileManager) {
        if (fileManager == null) {
            throw new IllegalArgumentException("File manager cannot be null");
        }
    }


    public static void validateScreens(List<Screen> screens) {
        if (screens == null) {
            throw new IllegalArgumentException("Screens cannot be null");
        }
    }

    public static void validatePlatform(String platform) {
        if (isEmpty(platform)) {
            throw new IllegalArgumentException("Platform cannot be empty");
        }
    }

    private static boolean isEmpty(String text) {
        return ((text == null) || text.isBlank());
    }

    public static void validateSource(Path source) {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }
    }

    public static void validateOutput(Path output) {
        if (output == null) {
            throw new IllegalArgumentException("Output cannot be null");
        }
    }

    public static void validateFramework(Framework framework) {
        if (framework == null) {
            throw new IllegalArgumentException("Framework cannot be null");
        }
    }

    public static void validateLines(List<String> lines) {
        if (lines == null) {
            throw new IllegalArgumentException("Lines cannot be null");
        }
    }

    public static void validateLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }
    }
}
