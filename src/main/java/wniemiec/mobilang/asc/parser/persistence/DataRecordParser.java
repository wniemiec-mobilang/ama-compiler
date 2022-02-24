package wniemiec.mobilang.asc.parser.persistence;

import java.lang.reflect.Method;
import org.json.JSONArray;
import org.json.JSONObject;

import wniemiec.mobilang.asc.models.persistence.PersistenceRecord;
import wniemiec.mobilang.asc.parser.exception.ParseException;
import wniemiec.util.java.StringUtils;


/**
 * Responsible por parsing a data record from PersistenceData.
 */
public class DataRecordParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String LABEL_INITIAL_VALUE;
    private final JSONObject dataRecord;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        LABEL_INITIAL_VALUE = "initialValue";
    }
    

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public DataRecordParser(JSONObject dataRecord) {
        this.dataRecord = dataRecord;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public PersistenceRecord<?> parse() throws ParseException {
        String type = normalizeType(dataRecord.getString("type"));

        try {
            Method getInstance = getMethodForParsing(type);

            return (PersistenceRecord<?>) getInstance.invoke(null);
        } 
        catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

    private String normalizeType(String type) {
        return StringUtils.capitalize(type);
    }

    private Method getMethodForParsing(String type) throws NoSuchMethodException {
        String methodName = buildMethodName(type);
        
        return DataRecordParser.class.getMethod(methodName);
    }

    private String buildMethodName(String type) {
        StringBuilder name = new StringBuilder();

        name.append("parse");
        name.append(type);
        name.append("Record");

        return name.toString();
    }

    @SuppressWarnings("unused") // Used only via reflection
    private PersistenceRecord<Integer> parseIntRecord() {
        Integer intValue = null;

        if (hasInitialValue()) {
            intValue = dataRecord.getInt(LABEL_INITIAL_VALUE);
        }

        return new PersistenceRecord<>(
            dataRecord.getString("name"),
            intValue
        );
    }

    private boolean hasInitialValue() {
        return !dataRecord.isNull(LABEL_INITIAL_VALUE);
    }

    @SuppressWarnings("unused") // Used only via reflection
    private PersistenceRecord<Double> parseFloatRecord() {
        Double floatValue = null;

        if (hasInitialValue()) {
            floatValue = dataRecord.getDouble(LABEL_INITIAL_VALUE);
        }

        return new PersistenceRecord<>(
            dataRecord.getString("name"),
            floatValue
        );
    }

    @SuppressWarnings("unused") // Used only via reflection
    private PersistenceRecord<String> parseStringRecord() {
        String strValue = null;

        if (hasInitialValue()) {
            strValue = dataRecord.getString(LABEL_INITIAL_VALUE);
        }

        return new PersistenceRecord<>(
            dataRecord.getString("name"),
            strValue
        );
    }

    @SuppressWarnings("unused") // Used only via reflection
    private PersistenceRecord<JSONObject> parseObjectRecord() {
        JSONObject objValue = null;

        if (hasInitialValue()) {
            objValue = dataRecord.getJSONObject(LABEL_INITIAL_VALUE);
        }

        return new PersistenceRecord<>(
            dataRecord.getString("name"),
            objValue
        );
    }

    @SuppressWarnings("unused") // Used only via reflection
    private PersistenceRecord<JSONArray> parseArrayRecord() {
        JSONArray arrValue = null;

        if (hasInitialValue()) {
            arrValue = dataRecord.getJSONArray(LABEL_INITIAL_VALUE);
        }

        return new PersistenceRecord<>(
            dataRecord.getString("name"),
            arrValue
        );
    }
}
