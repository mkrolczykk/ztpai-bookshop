package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.OrderEntity;
import pl.bookshop.bookservice.repository.facade.OrderRepository;

@Repository
interface SqlOrderRepository
        extends OrderRepository, JpaRepository<OrderEntity, Long> {}
