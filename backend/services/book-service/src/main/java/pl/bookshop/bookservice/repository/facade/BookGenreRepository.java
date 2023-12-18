package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.BookGenreEntity;

import java.util.List;
import java.util.Optional;

public interface BookGenreRepository {

    List<BookGenreEntity> findAll();

    Optional<String> findByGenreId(long id);

}
