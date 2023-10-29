package pl.bookshop.auth.util.exception.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bookshop.auth.util.messages.MessagesEnum;
import pl.bookshop.auth.util.messages.Translator;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDto {
    private String field;
    private String message;

    public ErrorDto(String field, MessagesEnum message) {
        this.field = field;
        this.message = Translator.translate(message);
    }

    public ErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static ErrorDto of(String field, MessagesEnum message) {
        return new ErrorDto(field, message);
    }

    public static ErrorDto of(String field, String message) {
        return new ErrorDto(field, message);
    }
}
