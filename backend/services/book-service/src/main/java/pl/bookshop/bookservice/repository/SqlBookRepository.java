package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.BookEntity;
import pl.bookshop.bookservice.repository.facade.BookRepository;

@Repository
interface SqlBookRepository
        extends BookRepository, JpaRepository<BookEntity, Long> {}
