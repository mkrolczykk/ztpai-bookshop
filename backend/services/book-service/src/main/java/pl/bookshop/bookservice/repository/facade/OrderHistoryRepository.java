package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.OrderHistoryEntity;

public interface OrderHistoryRepository {

    OrderHistoryEntity save(OrderHistoryEntity entity);

}
