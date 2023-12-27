package pl.bookshop.authservice.exception;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.bookshop.auth.util.exception.ValidationException;
import pl.bookshop.auth.util.exception.dto.ErrorDto;
import pl.bookshop.auth.util.exception.dto.ValidationErrorList;
import pl.bookshop.auth.util.messages.MessagesEnum;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@ControllerAdvice
@NoArgsConstructor
@Log4j2
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ValidationErrorList> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorList validationErrorList = ValidationErrorList.of(e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::mapToErrorDto)
                .collect(Collectors.toSet()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorList);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ValidationErrorList> onRuntimeException(RuntimeException e) {
        ValidationErrorList errorList = ValidationErrorList.of("message", MessagesEnum.INTERNAL_SERVER_ERROR);
        return errorList.createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<Object> onAccessDeniedException(AccessDeniedException e, WebRequest request) {

        final String message = "Access Denied";

        log.warn("Captured AccessDeniedException: " + e.getMessage());

        ExceptionResponse response =
                new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ValidationErrorList> onValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getValidationErrorList());
    }

    private ErrorDto mapToErrorDto(ObjectError error) {
        FieldError fieldError = (FieldError) error;
        return ErrorDto.of(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
