package pl.bookshop.bookservice.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShoppingCartTotalItemsCountResp {

    private String action;

    private int result;
}
