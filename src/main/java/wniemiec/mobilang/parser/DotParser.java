package wniemiec.mobilang.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DotParser implements Parser {

    private Path dotFile;
    private Path outputLocation;
    
    public DotParser(Path dotFile, Path outputLocation) {
        this.dotFile = dotFile;
        this.outputLocation = outputLocation;
    }

    public void parse() throws IOException {
        for (String line : Files.readAllLines(dotFile)) {
            System.out.println(line);
        }
    }
}
