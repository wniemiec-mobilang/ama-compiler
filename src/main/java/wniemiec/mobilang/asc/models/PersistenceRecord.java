package wniemiec.mobilang.asc.models;

public class PersistenceRecord<T> {

    private String name;
    private T initialValue;

    public PersistenceRecord(String name, T initialValue) {
        this.name = name;
        this.initialValue = initialValue;
    }

    public String getName() {
        return name;
    }

    public T getInitialValue() {
        return initialValue;
    }

    public boolean hasNoInitialValue() {
        return (initialValue == null);
    }
    
    
}
