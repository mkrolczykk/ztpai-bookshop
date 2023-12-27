package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.entity.BookEntity;

public interface BookRepository {

    @Query(value = """
        SELECT EXISTS(
            SELECT 1 FROM book b
            WHERE b.book_id = :bookId
        )
    """, nativeQuery = true)
    Boolean existsById(@Param("bookId") long bookId);

    @Query(value = """
        SELECT EXISTS(
            SELECT 1 FROM book b
            WHERE LOWER(b.title) = :title
        )
    """, nativeQuery = true)
    boolean existsByTitle(@Param("title") String title);

    BookEntity save(BookEntity entity);

}
