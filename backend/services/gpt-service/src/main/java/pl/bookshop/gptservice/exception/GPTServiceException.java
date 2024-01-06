package pl.bookshop.gptservice.exception;

public final class GPTServiceException extends RuntimeException {

    public GPTServiceException() { super(); }

    public GPTServiceException(String message) { super(message); }

    public GPTServiceException(String message, Throwable t) { super(message, t); }

}
