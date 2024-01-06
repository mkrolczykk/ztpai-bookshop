package pl.bookshop.notificationsservice.exception;

public final class MailServiceException extends RuntimeException {

    public MailServiceException() { super(); }

    public MailServiceException(String message) { super(message); }

    public MailServiceException(String message, Throwable t) { super(message, t); }

}
