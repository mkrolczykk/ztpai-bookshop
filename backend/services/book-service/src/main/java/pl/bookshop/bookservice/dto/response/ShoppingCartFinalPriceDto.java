package pl.bookshop.bookservice.dto.response;

import java.math.BigDecimal;

public interface ShoppingCartFinalPriceDto {

    BigDecimal getTotal();

    String getCurrency();

}
