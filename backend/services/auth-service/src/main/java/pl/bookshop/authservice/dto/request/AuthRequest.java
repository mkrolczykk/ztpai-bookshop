package pl.bookshop.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import pl.bookshop.auth.util.messages.Messages;

@FieldNameConstants
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String email;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String password;

}
