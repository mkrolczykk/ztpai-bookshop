package pl.bookshop.bookservice.entity.composedKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookPriceId implements Serializable {

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

}
