package pl.bookshop.bookservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bookshop.auth.util.messages.Messages;
import pl.bookshop.bookservice.entity.BookEntity;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookReq {

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String title;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String author;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String summary;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String description;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String genre;

    @NotNull(message = Messages.EMPTY_FIELD)
    private int numPages;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String language;

    @DecimalMin(value = "0.0", inclusive = false, message = "Invalid price format")
    @Digits(integer=6, fraction=2, message = "Invalid price format")
    private BigDecimal price;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String currency;

    @NotBlank(message = Messages.EMPTY_FIELD)
    private String publisher;

    public BookEntity toBookEntity(String slug, long languageId, long genreId, long publisherId) {
        return BookEntity.builder()
                .title(this.title)
                .summary(this.summary)
                .description(this.description)
                .numPages(this.numPages)
                .slug(slug)
                .languageId(languageId)
                .genreId(genreId)
                .publisherId(publisherId)
                .build();
    }

}
