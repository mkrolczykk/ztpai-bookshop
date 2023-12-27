package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.BookLanguageEntity;

import java.util.Optional;

public interface BookLanguageRepository {

    boolean existsByLanguageName(String name);

    Optional<BookLanguageEntity> findByLanguageNameIgnoreCase(String name);

    BookLanguageEntity getBookLanguageEntityByLanguageName(String name);

}
