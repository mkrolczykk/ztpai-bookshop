package pl.bookshop.bookservice.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FavoritesBookActionResp {

    private String action;

    private String message;

}
