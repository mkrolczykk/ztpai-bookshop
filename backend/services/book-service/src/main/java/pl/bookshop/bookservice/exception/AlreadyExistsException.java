package pl.bookshop.bookservice.exception;

public class AlreadyExistsException extends RuntimeException{

    public AlreadyExistsException() { super(); }

    public AlreadyExistsException(String message) { super(message); }

    public AlreadyExistsException(String message, Throwable t) { super(message, t); }

}
