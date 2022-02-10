package wniemiec.mobilang.asc.models.persistence;

import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for representing persistence data.
 */
public class PersistenceData {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<PersistenceRecord<?>> records;
    private String type;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public PersistenceData() {
        records = new ArrayList<>();
        type = "";
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void addRecord(PersistenceRecord<?> persistenceRecord) {
        records.add(persistenceRecord);
    }


    //-------------------------------------------------------------------------
    //		Getters & Setters
    //-------------------------------------------------------------------------
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PersistenceRecord<?>> getVariables() {
        return records;
    }
}
