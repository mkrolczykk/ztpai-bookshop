package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.BookGenreEntity;
import pl.bookshop.bookservice.repository.facade.BookGenreRepository;

@Repository
interface SqlBookGenreRepository
        extends BookGenreRepository, JpaRepository<BookGenreEntity, Long> {

}
