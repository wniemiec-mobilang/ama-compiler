package wniemiec.mobilang.asc.parser.screens.behavior.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import wniemiec.mobilang.asc.models.behavior.Expression;
import wniemiec.mobilang.asc.parser.exception.ParserFactoryException;

public class ExpressionJsonParserFactory {
    
    public static ExpressionJsonParser get(String expressionType) throws ParserFactoryException {
        String expressionName = expressionType.replace("Expression", "");
        try {
            Class<?> c = Class.forName("wniemiec.mobilang.asc.parser.screens.behavior.expression." + expressionName + "ExpressionJsonParser");
            Method m = c.getMethod("getInstance");
            return (ExpressionJsonParser) m.invoke(c);
        } 
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ParserFactoryException(e.getMessage());
        }
        //return FACTORY.get(expressionType);
    }
}
