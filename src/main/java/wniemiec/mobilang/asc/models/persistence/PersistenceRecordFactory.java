package wniemiec.mobilang.asc.models.persistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import wniemiec.io.java.Consolex;
import wniemiec.mobilang.asc.parser.exception.FactoryException;
import wniemiec.mobilang.asc.utils.StringUtils;

public class PersistenceRecordFactory {

    public static PersistenceRecord<?> of(String type) throws FactoryException {
        String normalizedType = StringUtils.capitalize(type);
        try {
            Method getInstance = PersistenceRecordFactory.class.getMethod("getInstance" + normalizedType);

            return (PersistenceRecord<?>) getInstance.invoke(null);
        } 
        catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
        
    }
    
    private static PersistenceRecord<String> getInstanceString() {
        return new PersistenceRecord<>();
    }
}