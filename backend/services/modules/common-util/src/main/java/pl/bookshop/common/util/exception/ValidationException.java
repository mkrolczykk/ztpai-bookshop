package pl.bookshop.common.util.exception;

import lombok.Getter;
import pl.bookshop.common.util.exception.dto.ValidationErrorList;


public class ValidationException extends RuntimeException {

    @Getter
    private final ValidationErrorList validationErrorList;

    public ValidationException(ValidationErrorList validationErrorList) {
        this.validationErrorList = validationErrorList;
    }

}
