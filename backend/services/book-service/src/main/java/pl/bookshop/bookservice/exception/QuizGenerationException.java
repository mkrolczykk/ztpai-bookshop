package pl.bookshop.bookservice.exception;

public final class QuizGenerationException extends RuntimeException {

    public QuizGenerationException() { super(); }

    public QuizGenerationException(String message) { super(message); }

    public QuizGenerationException(String message, Throwable t) { super(message, t); }

}
