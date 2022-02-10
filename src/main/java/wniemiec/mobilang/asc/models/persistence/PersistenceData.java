package wniemiec.mobilang.asc.models.persistence;

import java.util.ArrayList;
import java.util.List;

public class PersistenceData {

    private String type = "";
    private List<PersistenceRecord<?>> records;

    public PersistenceData() {
        records = new ArrayList<>();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<PersistenceRecord<?>> getVariables() {
        return records;
    }

    public void addRecord(PersistenceRecord<?> record) {
        records.add(record);
    }
    
}
