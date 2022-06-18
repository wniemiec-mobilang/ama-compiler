package util.terminal;

import java.util.ArrayList;
import java.util.List;
import wniemiec.io.java.OutputTerminal;


public class MockOutputTerminal implements OutputTerminal {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private List<String> log;
    private List<String> errorLog;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public MockOutputTerminal() {
        log = new ArrayList<>();
        errorLog = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public void receive(String message) {
        log.add(message);
    }

    @Override
    public void receiveError(String message) {
        errorLog.add(message);
    }

    @Override
    public void clear() {
        log.add("clear");
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    @Override
    public List<String> getHistory() {
        return log;
    }

    @Override
    public List<String> getErrorHistory() {
        return errorLog;
    }
}
