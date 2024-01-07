package pl.bookshop.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import pl.bookshop.auth.util.messages.Messages;

import java.io.Serializable;

@Data
@Builder
@ToString
public class UserRegisterSuccessEvent implements Serializable {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String messageLanguage;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String email;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String name;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String surname;

    @NotNull(message = Messages.EMPTY_FIELD)
    private String username;

}
