package pl.bookshop.bookservice.dto.response;

import java.math.BigDecimal;

public interface BookDetailDto {

    Long getBookId();

    String getTitle();

    String getAuthors();

    String getCategory();

    String getSummary();

    String getDescription();

    BigDecimal getPrice();

    String getCurrency();

    Integer getNumPages();

    String getLanguages();

    String getAddedAt();

}
