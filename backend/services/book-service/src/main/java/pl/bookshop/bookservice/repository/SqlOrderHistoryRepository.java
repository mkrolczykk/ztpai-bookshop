package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.OrderHistoryEntity;
import pl.bookshop.bookservice.repository.facade.OrderHistoryRepository;

@Repository
interface SqlOrderHistoryRepository
        extends OrderHistoryRepository, JpaRepository<OrderHistoryEntity, Long> {}
