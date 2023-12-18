package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.OrderLineEntity;

public interface OrderLineRepository {

    OrderLineEntity save(OrderLineEntity entity);

}
