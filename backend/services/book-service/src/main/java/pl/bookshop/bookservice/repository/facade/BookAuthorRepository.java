package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.BookAuthorEntity;

public interface BookAuthorRepository {

    BookAuthorEntity save(BookAuthorEntity entity);

}
