package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderStatusRepository {

    @Query(value = """
        SELECT
            status_id
        FROM
            order_status
        WHERE
            status = :status
        LIMIT 1
    """, nativeQuery = true)
    Optional<Long> getStatusIdByStatus(@Param("status") String status);

}
