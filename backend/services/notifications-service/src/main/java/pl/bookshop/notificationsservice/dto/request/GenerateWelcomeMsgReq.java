package pl.bookshop.notificationsservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import pl.bookshop.auth.util.messages.Messages;

import java.io.Serializable;

@Data
@ToString
@Builder
public class GenerateWelcomeMsgReq implements Serializable {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String messageLanguage;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String name;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String surname;

    @NotNull(message = Messages.EMPTY_FIELD)
    private String username;

}