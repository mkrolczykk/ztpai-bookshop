package pl.bookshop.bookservice.dto.response;


import java.math.BigDecimal;

public interface OrderLineDto {

    Long getBookId();

    Integer getAmount();

    BigDecimal getPrice();

}
