package pl.bookshop.bookservice.dto;

import java.math.BigDecimal;

public interface TopSoldBooksDto {

    Long getBookId();

    String getTitle();

    String getAuthors();

    BigDecimal getPrice();

    String getCurrency();

    Long getTotalSold();

}
