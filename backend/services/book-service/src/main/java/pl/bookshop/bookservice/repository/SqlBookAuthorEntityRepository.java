package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.BookAuthorEntity;
import pl.bookshop.bookservice.repository.facade.BookAuthorEntityRepository;

@Repository
interface SqlBookAuthorEntityRepository
        extends BookAuthorEntityRepository, JpaRepository<BookAuthorEntity, Long> {}
