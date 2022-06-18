package util.terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wniemiec.io.java.InputTerminal;


public class MockInputTerminal implements InputTerminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> log;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MockInputTerminal() {
        log = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void exec(String... commands) throws IOException {
        for (String command : commands) {
            String parsedCommand = command;

            parsedCommand = parsedCommand.replaceAll(".+/keytool", "keytool");
            parsedCommand = parsedCommand.replaceAll(".+/gradlew", "gradlew");

            log.add(parsedCommand);
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public List<String> getLog() {
        return log;
    }
}
