package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.BookLanguageEntity;
import pl.bookshop.bookservice.repository.facade.BookLanguageEntityRepository;

@Repository
interface SqlBookLanguageEntityRepository
        extends BookLanguageEntityRepository, JpaRepository<BookLanguageEntity, Long> {}
