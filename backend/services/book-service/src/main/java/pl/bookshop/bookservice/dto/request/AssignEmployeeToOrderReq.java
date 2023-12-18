package pl.bookshop.bookservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bookshop.auth.util.messages.Messages;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignEmployeeToOrderReq {

    @NotNull(message = Messages.EMPTY_FIELD)
    private Long orderId;

    @NotNull(message = Messages.EMPTY_FIELD)
    private Long employeeId;

}
