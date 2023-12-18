package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.OrderStatusEntity;
import pl.bookshop.bookservice.repository.facade.OrderStatusRepository;

@Repository
interface SqlOrderStatusRepository
        extends OrderStatusRepository, JpaRepository<OrderStatusEntity, Long> {}
