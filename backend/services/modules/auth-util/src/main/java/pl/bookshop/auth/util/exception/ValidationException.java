package pl.bookshop.auth.util.exception;

import lombok.Getter;
import pl.bookshop.auth.util.exception.dto.ValidationErrorList;


public class ValidationException extends RuntimeException {

    @Getter
    private final ValidationErrorList validationErrorList;

    public ValidationException(ValidationErrorList validationErrorList) {
        this.validationErrorList = validationErrorList;
    }

}
