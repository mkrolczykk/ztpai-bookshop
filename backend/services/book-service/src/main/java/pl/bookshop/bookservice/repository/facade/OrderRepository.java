package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.dto.response.BookOrderDto;
import pl.bookshop.bookservice.entity.OrderEntity;

import java.util.List;

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

    @Query(value = """
        SELECT
            o.order_id AS orderId,
            TO_CHAR(o.created_at, \'YYYY-MM-DD HH24:MI:SS\') AS orderTime,
            SUM(ol.total_price) AS total,
            c.shortcut AS currency,
            os.status AS orderStatus,
            u.user_id AS orderExecId,
            CONCAT(u.name, \' \', u.surname) AS orderExec
        FROM
            "order" AS o
        INNER JOIN
            order_line AS ol ON o.order_id = ol.order_id
        INNER JOIN
            currency AS c ON ol.currency_id = c.currency_id
        INNER JOIN
            order_history AS oh ON o.order_id = oh.order_id
        INNER JOIN
            order_status AS os ON oh.status_id = os.status_id
        LEFT JOIN
            system_user AS u ON o.order_exec = u.user_id
        WHERE
            os.status = \'Order Received\'
        GROUP BY
            o.order_id,
            o.created_at,
            c.shortcut,
            os.status,
            u.user_id,
            u.name,
            u.surname
        ORDER BY
            o.created_at ASC
    """, nativeQuery = true)
    List<BookOrderDto> getOrders();

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
