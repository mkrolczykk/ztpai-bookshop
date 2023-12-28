package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.dto.response.BookDetailDto;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.dto.response.TopSoldBookDto;
import pl.bookshop.bookservice.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    @Query(value = """
        SELECT EXISTS(
            SELECT 1 FROM book b
            WHERE b.book_id = :bookId
        )
    """, nativeQuery = true)
    Boolean existsById(@Param("bookId") long bookId);

    @Query(value = """
        SELECT
            book.book_id AS bookId,
            book.title AS title,
            STRING_AGG(author.author_name, \', \') AS authors,
            book_genre.genre AS category,
            book.summary AS summary,
            book.description AS description,
            book_price.price AS price,
            currency.shortcut AS currency,
            book.num_pages AS numPages,
            STRING_AGG(book_language.language_name, \', \') AS languages,
            TO_CHAR(book.created_at, \'YYYY-MM-DD\') AS addedAt
        FROM book
        JOIN book_author ON book.book_id = book_author.book_id
        JOIN book_genre ON book.genre_id = book_genre.genre_id
        JOIN author ON author.author_id = book_author.author_id
        JOIN book_price ON book.book_id = book_price.book_id
        JOIN book_language ON book.language_id = book_language.language_id
        JOIN currency ON book_price.currency_id = currency.currency_id
        WHERE
            book.book_id = :bookId AND
            currency.shortcut = :currency
        GROUP BY
            book.book_id,
            book.title,
            book_genre.genre,
            book_price.price,
            currency.shortcut,
            book.num_pages,
            book.created_at
    """, nativeQuery = true)
    Optional<BookDetailDto> getBookById(@Param("bookId") long bookId, @Param("currency") String currency);

    @Query(value = """
        SELECT
            book.book_id AS bookid,
            book.title AS title,
            STRING_AGG(author.author_name, \', \') AS authors,
            book_price.price AS price,
            currency.shortcut AS currency
        FROM book
        JOIN book_author ON book.book_id = book_author.book_id
        JOIN author ON author.author_id = book_author.author_id
        JOIN book_price ON book.book_id = book_price.book_id
        JOIN currency ON book_price.currency_id = currency.currency_id
        JOIN book_genre ON book_genre.genre_id = book.genre_id
        WHERE
            LOWER(book_genre.genre) = LOWER(:categoryType) AND
            currency.shortcut = :currency
        GROUP BY
            book.book_id,
            book.title,
            book_price.price,
            currency.shortcut,
            book.created_at
        ORDER BY
            book.created_at DESC
    """, nativeQuery = true)
    List<BookDto> getBooksFromGivenCategory(@Param("categoryType") String category, @Param("currency") String currency);

    @Query(value = """
        SELECT
            book.book_id AS bookid,
            book.title AS title,
            STRING_AGG(author.author_name, \', \') AS authors,
            book_price.price AS price,
            currency.shortcut AS currency
        FROM book
        JOIN book_author ON book.book_id = book_author.book_id
        JOIN author ON author.author_id = book_author.author_id
        JOIN book_price ON book.book_id = book_price.book_id
        JOIN currency ON book_price.currency_id = currency.currency_id
        WHERE
            (LOWER(book.title) LIKE :searchkey OR LOWER(author.author_name) LIKE :searchkey) AND
            currency.shortcut = :currency
        GROUP BY book.book_id, book.title, book_price.price, currency.shortcut
    """, nativeQuery = true)
    List<BookDto> getBooksByTitleOrAuthor(@Param("searchkey") String searchKey, @Param("currency") String currency);

    @Query(value = """
        SELECT
            book.book_id AS bookId,
            book.title AS title,
            STRING_AGG(author.author_name, \', \') AS authors,
            book_price.price AS price,
            currency.shortcut AS currency,
            sales.total_amount AS totalSold
        FROM book
        JOIN book_author ON book.book_id = book_author.book_id
        JOIN author ON author.author_id = book_author.author_id
        JOIN book_price ON book.book_id = book_price.book_id
        JOIN currency ON book_price.currency_id = currency.currency_id
        JOIN (
            SELECT
                ol.book_id AS book_id,
                SUM(ol.amount) AS total_amount
            FROM order_line ol
            JOIN "order" o ON o.order_id = ol.order_id
            JOIN order_history oh ON o.order_id = oh.order_id
            JOIN order_status os ON os.status_id = oh.status_id
            WHERE
                os.status in (\'Order Received\', \'Pending Delivery\', \'Delivered\', \'Delivery In Progress\')
            GROUP BY
                ol.book_id
            ORDER BY
                total_amount DESC
        ) AS sales ON book.book_id = sales.book_id
        WHERE
            book.created_at <= NOW() AND
            currency.shortcut = :currency
        GROUP BY
            book.book_id,
            book.title,
            book_price.price,
            currency.shortcut,
            book.created_at,
            sales.total_amount
        ORDER BY
            sales.total_amount DESC
        LIMIT :limit
    """, nativeQuery = true)
    List<TopSoldBookDto> getTopSoldBooks(@Param("currency") String currency, @Param("limit") int limit);

    @Query(value = """
        SELECT EXISTS(
            SELECT 1 FROM book b
            WHERE LOWER(b.title) = :title
        )
    """, nativeQuery = true)
    boolean existsByTitle(@Param("title") String title);

    BookEntity save(BookEntity entity);

}
