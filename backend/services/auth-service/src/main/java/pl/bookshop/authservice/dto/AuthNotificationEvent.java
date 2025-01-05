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
public class AuthNotificationEvent implements Serializable {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String channel;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String receiver;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String content;

}
