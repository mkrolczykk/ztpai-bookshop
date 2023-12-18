package pl.bookshop.bookservice.dto.response;

import lombok.*;
import pl.bookshop.bookservice.entity.BookGenreEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GenresResp {

    private Long genreId;

    private String genre;

    public static GenresResp toGenresResp(BookGenreEntity genreEntity) {
        return GenresResp.builder()
                .genreId(genreEntity.getGenreId())
                .genre(genreEntity.getGenre())
                .build();
    }

}
