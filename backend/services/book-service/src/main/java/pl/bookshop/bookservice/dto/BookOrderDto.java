package pl.bookshop.bookservice.dto;

import java.math.BigDecimal;
public interface BookOrderDto {

    Long getOrderId();

    String getOrderTime();

    BigDecimal getTotal();

    String getCurrency();

    String getOrderStatus();

    Long getOrderExecId();

    String getOrderExec();

}
