package pl.bookshop.gptservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.bookshop.auth.util.messages.Messages;

import java.io.Serializable;

@Data
public class RegisteredUserInfoDto implements Serializable {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String messageLanguage;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String name;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String surname;

    @NotNull(message = Messages.EMPTY_FIELD)
    private String username;

}