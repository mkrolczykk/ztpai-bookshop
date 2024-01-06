package pl.bookshop.notificationsservice.exception;

public final class PersonalizedMessageGenerationException extends RuntimeException {

    public PersonalizedMessageGenerationException() { super(); }

    public PersonalizedMessageGenerationException(String message) { super(message); }

    public PersonalizedMessageGenerationException(String message, Throwable t) { super(message, t); }

}
