package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository {

    @Query(value = """
        SELECT EXISTS(
            SELECT 1 FROM book b
            WHERE b.book_id = :bookId
        )
    """, nativeQuery = true)
    Boolean existsById(@Param("bookId") long bookId);

}
