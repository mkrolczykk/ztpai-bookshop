package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.dto.OrderLineDto;
import pl.bookshop.bookservice.entity.ShoppingCartEntity;
import pl.bookshop.bookservice.entity.composedKey.ShoppingCartId;

import java.util.List;

public interface ShoppingCartRepository {

    boolean existsById(ShoppingCartId id);

    ShoppingCartEntity save(ShoppingCartEntity entity);

    @Query(value = """
        SELECT COUNT(*)
        FROM shopping_cart
        WHERE user_id = :userId
    """, nativeQuery = true)
    int countShoppingCartItems(@Param("userId") long userId);

    @Modifying
    @Query(value = """
        UPDATE
            shopping_cart
        SET
            "amount" = "amount" + 1
        WHERE
            user_id = :userId AND
            book_id = :bookId
    """, nativeQuery = true)
    void increaseAmountOfGivenBook(@Param("userId") long userId, @Param("bookId") long bookId);

    void deleteById(ShoppingCartId id);

    @Query(value = """
        SELECT
            sc.book_id AS bookId,
            sc.amount AS amount,
            bp.price AS price
        FROM
            shopping_cart sc
        INNER JOIN
            book b ON sc.book_id = b.book_id
        INNER JOIN
            book_price bp ON bp.book_id = b.book_id AND bp.currency_id = :currencyId
        WHERE
            sc.user_id = :userId
    """, nativeQuery = true)
    List<OrderLineDto> getOrderLines(@Param("userId") long userId, @Param("currencyId") long currencyId);

    @Modifying
    @Query(value = """
        UPDATE
            shopping_cart
        SET
            amount =
                CASE WHEN amount > 1
                    THEN amount - 1
                    ELSE amount
                END
        WHERE
            user_id = :userId AND
            book_id = :bookId
    """, nativeQuery = true)
    void decreaseAmountOfGivenBook(@Param("userId") long userId, @Param("bookId") long bookId);

    @Modifying
    @Query(value = """
        DELETE FROM shopping_cart
        WHERE
            user_id = :userId
    """, nativeQuery = true)
    void emptyShoppingCart(@Param("userId") long userId);

}
