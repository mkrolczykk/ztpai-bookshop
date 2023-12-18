package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.OrderLineEntity;
import pl.bookshop.bookservice.repository.facade.OrderLineRepository;

@Repository
interface SqlOrderLineRepository
        extends OrderLineRepository, JpaRepository<OrderLineEntity, Long> {}
