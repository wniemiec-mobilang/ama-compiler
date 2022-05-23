package wniemiec.mobilang.ama.models;


/**
 * Represents an interval between two numbers,
 * 
 * @param       T Interval type
 */
public class Range<T extends Number> {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final T begin;
    private final T end;
 

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Range(T begin, T end) {
        this.begin = begin;
        this.end = end;
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public T getBegin() {
        return begin;
    }

    public T getEnd() {
        return end;
    }
}
