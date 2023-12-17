package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.SystemUserFavoriteBookEntity;
import pl.bookshop.bookservice.repository.facade.SystemUserFavoriteBookEntityRepository;

@Repository
interface SqlSystemUserFavoriteBookEntityRepository
        extends SystemUserFavoriteBookEntityRepository, JpaRepository<SystemUserFavoriteBookEntity, Long> {}
