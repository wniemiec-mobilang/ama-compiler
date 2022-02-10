package wniemiec.mobilang.asc.models.persistence;


/**
 * Responsible for representing a record from persistence data.
 */
public class PersistenceRecord<T> {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String name;
    private final T initialValue;


    //-------------------------------------------------------------------------
    //		Constructors
    //-------------------------------------------------------------------------
    public PersistenceRecord(String name, T initialValue) {
        this.name = name;
        this.initialValue = initialValue;
    }

    public PersistenceRecord() {
        this("", null);
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public boolean hasNoInitialValue() {
        return (initialValue == null);
    }

    public PersistenceRecord<T> with(String name, T initialValue) {
        return new PersistenceRecord<>(name, initialValue);
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public T getInitialValue() {
        return initialValue;
    }
}