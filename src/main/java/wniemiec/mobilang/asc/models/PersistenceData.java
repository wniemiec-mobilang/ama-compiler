package wniemiec.mobilang.asc.models;

import java.util.ArrayList;
import java.util.List;

public class PersistenceData {

    private String type = "";

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<PersistenceVariable> getVariables() {
        return new ArrayList<>();
    }
    
}
