package pl.bookshop.authservice.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private LocalDateTime timestamp;

    private String message;

    private String other;

    public ExceptionResponse(final LocalDateTime timestamp, final String message, final String other) {
        this.timestamp = timestamp;
        this.message = message;
        this.other = other;
    }

    public LocalDateTime getTimestamp() {return timestamp;}
    public void setTimestamp(final LocalDateTime timestamp) {this.timestamp = timestamp;}

    public String getMessage() {return message;}
    public void setMessage(final String message) {this.message = message;}

    public String getOther() {return other;}
    public void setOther(final String other) {this.other = other;}
}
