package pl.bookshop.bookservice.exception;

public final class OperationProcessingException extends RuntimeException {

    public OperationProcessingException() { super(); }

    public OperationProcessingException(String message) { super(message); }

    public OperationProcessingException(String message, Throwable t) { super(message, t); }

}
