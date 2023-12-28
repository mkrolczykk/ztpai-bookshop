package pl.bookshop.bookservice.dto.response;

import java.math.BigDecimal;

public interface UserShoppingCartDto {

    Long getBookId();

    String getBookName();

    BigDecimal getPrice();

    Long getAmount();

    String getCurrency();

    BigDecimal getTotal();

}
