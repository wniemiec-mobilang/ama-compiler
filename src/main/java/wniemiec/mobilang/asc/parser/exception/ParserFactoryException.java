package wniemiec.mobilang.asc.parser.exception;

// throw when there is no desired instance in a parser factory
public class ParserFactoryException extends Exception {
    
    public ParserFactoryException() {
    }

    public ParserFactoryException(String message) {
        super(message);
    }
}
