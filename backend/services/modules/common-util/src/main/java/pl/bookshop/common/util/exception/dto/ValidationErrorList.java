package pl.bookshop.common.util.exception.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.bookshop.common.util.messages.MessagesEnum;
import pl.bookshop.common.util.messages.Translator;
import pl.bookshop.common.util.exception.ValidationException;

import java.util.HashSet;
import java.util.Set;

public class ValidationErrorList {

    @Getter
    private final Set<ErrorDto> errors;

    private ValidationErrorList() {
        this.errors = new HashSet<>();
    }

    private ValidationErrorList(ErrorDto error) {
        this.errors = new HashSet<>();
        errors.add(error);
    }

    private ValidationErrorList(Set<ErrorDto> errors) {
        this.errors = errors;
    }

    public ValidationErrorList(String field, MessagesEnum message) {
        this.errors = new HashSet<>();
        errors.add(ErrorDto.of(field, Translator.translate(message)));
    }

    public ValidationErrorList(String field, String message) {
        this.errors = new HashSet<>();
        errors.add(ErrorDto.of(field, message));
    }

    public static ValidationErrorList empty() {
        return new ValidationErrorList();
    }

    public static ValidationErrorList of(ErrorDto error) {
        return new ValidationErrorList(error);
    }

    public static ValidationErrorList of(Set<ErrorDto> errors) {
        return new ValidationErrorList(errors);
    }

    public static ValidationErrorList of(String field, MessagesEnum message) {
        return new ValidationErrorList(field, message);
    }

    public static ValidationErrorList of(String field, String message) {
        return new ValidationErrorList(field, message);
    }

    public void add(ErrorDto error) {
        this.errors.add(error);
    }

    public void add(String field, MessagesEnum message) {
        this.errors.add(ErrorDto.of(field, message));
    }

    public void add(String field, String message) {
        this.errors.add(ErrorDto.of(field, message));
    }

    public ResponseEntity<ValidationErrorList> createResponseEntity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this);
    }

    public ResponseEntity<ValidationErrorList> createResponseEntity(HttpStatus status) {
        return ResponseEntity.status(status).body(this);
    }

    public void throwIfNotEmpty() {
        if (!errors.isEmpty())
            throw new ValidationException(this);
    }

}
