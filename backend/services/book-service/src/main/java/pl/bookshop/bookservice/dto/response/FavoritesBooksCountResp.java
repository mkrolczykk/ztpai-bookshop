package pl.bookshop.bookservice.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FavoritesBooksCountResp {

    private String action;

    private int result;

}
