package pl.bookshop.authservice.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import pl.bookshop.common.util.messages.Messages;

@FieldNameConstants
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailValidationRequest {

    @Email(message = Messages.BAD_EMAIL)
    private String email;

}
