package pl.bookshop.bookservice.dto.response;

import java.math.BigDecimal;

public interface TopSoldBookDto {

    Long getBookId();

    String getTitle();

    String getAuthors();

    BigDecimal getPrice();

    String getCurrency();

    Long getTotalSold();

}
