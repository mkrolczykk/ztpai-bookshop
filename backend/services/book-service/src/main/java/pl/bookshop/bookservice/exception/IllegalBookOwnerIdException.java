package pl.bookshop.bookservice.exception;

public final class IllegalBookOwnerIdException extends RuntimeException {

    public IllegalBookOwnerIdException() { super(); }

    public IllegalBookOwnerIdException(String message) { super(message); }

    public IllegalBookOwnerIdException(String message, Throwable t) { super(message, t); }

}
