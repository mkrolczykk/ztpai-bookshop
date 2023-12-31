package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.SystemUserFavoriteBookEntity;
import pl.bookshop.bookservice.entity.composedKey.SystemUserFavoriteBookId;
import pl.bookshop.bookservice.repository.facade.SystemUserFavoriteBookRepository;

@Repository
interface SqlSystemUserFavoriteBookRepository
        extends SystemUserFavoriteBookRepository, JpaRepository<SystemUserFavoriteBookEntity, SystemUserFavoriteBookId> {}
