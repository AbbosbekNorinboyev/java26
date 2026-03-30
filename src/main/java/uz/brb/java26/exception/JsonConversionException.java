package uz.brb.java26.exception;

public class JsonConversionException extends RuntimeException {
    public JsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}