package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.entity.SystemUserFavoriteBookEntity;
import pl.bookshop.bookservice.entity.composedKey.SystemUserFavoriteBookId;

import java.util.List;

public interface SystemUserFavoriteBookRepository {

    boolean existsById(SystemUserFavoriteBookId id);

    SystemUserFavoriteBookEntity save(SystemUserFavoriteBookEntity entity);

    @Query(value = """
        SELECT COUNT(*)
        FROM system_user_favorite_book
        WHERE user_id = :userId
    """, nativeQuery = true)
    int countUserFavoriteBooks(@Param("userId") long userId);

    @Query(value = """
        SELECT
            book.book_id AS bookid,
            book.title AS title,
            STRING_AGG(author.author_name, \', \') AS authors,
            book_price.price AS price,
            currency.shortcut AS currency
        FROM
            system_user_favorite_book AS favorite
        JOIN book ON favorite.book_id = book.book_id
        JOIN book_author ON book.book_id = book_author.book_id
        JOIN author ON author.author_id = book_author.author_id
        JOIN book_price ON book.book_id = book_price.book_id
        JOIN currency ON book_price.currency_id = currency.currency_id
        WHERE
            favorite.user_id = :userId
        GROUP BY
            book.book_id,
            book.title,
            book_price.price,
            currency.shortcut,
            favorite.added_at
        ORDER BY favorite.added_at DESC
        LIMIT :limit
    """, nativeQuery = true)
    List<BookDto> getUserFavoriteBooks(@Param("userId") long userId, @Param("limit") long limit);

    void deleteById(SystemUserFavoriteBookId id);

}
