package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.ShoppingCartEntity;
import pl.bookshop.bookservice.repository.facade.ShoppingCartEntityRepository;

@Repository
interface SqlShoppingCartEntityRepository
        extends ShoppingCartEntityRepository, JpaRepository<ShoppingCartEntity, Long> {}
