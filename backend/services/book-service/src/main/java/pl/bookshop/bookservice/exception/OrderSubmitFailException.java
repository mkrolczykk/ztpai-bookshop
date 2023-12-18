package pl.bookshop.bookservice.exception;

public final class OrderSubmitFailException extends RuntimeException {

    public OrderSubmitFailException() { super(); }

    public OrderSubmitFailException(String message) { super(message); }

    public OrderSubmitFailException(String message, Throwable t) { super(message, t); }

}
