package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.entity.OrderEntity;

public interface OrderRepository {

    @Modifying
    @Query(value = """
        UPDATE "order"
        SET
            order_exec = :employeeId
        WHERE
            order_id = :orderId
    """, nativeQuery = true)
    void assignEmployeeToOrder(@Param("orderId") long orderId, @Param("employeeId") long employeeId);

    @Modifying
    @Query(value = """
        UPDATE order_history
        SET
            status_id = (SELECT status_id FROM order_status WHERE status = \'Pending Delivery\')
        WHERE
            order_id = :orderId AND
            status_id = (SELECT status_id FROM order_status WHERE status = \'Order Received\')
    """, nativeQuery = true)
    void updateOrderStatusToPendingDelivery(@Param("orderId") long orderId);

    OrderEntity save(OrderEntity entity);
}
