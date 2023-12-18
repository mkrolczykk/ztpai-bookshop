package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.ShoppingCartEntity;
import pl.bookshop.bookservice.entity.composedKey.ShoppingCartId;
import pl.bookshop.bookservice.repository.facade.ShoppingCartRepository;

@Repository
interface SqlShoppingCartRepository
        extends ShoppingCartRepository, JpaRepository<ShoppingCartEntity, ShoppingCartId> {}
