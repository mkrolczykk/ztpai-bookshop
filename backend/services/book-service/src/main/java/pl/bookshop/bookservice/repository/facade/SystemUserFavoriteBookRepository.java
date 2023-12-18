package pl.bookshop.bookservice.repository.facade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bookshop.bookservice.entity.SystemUserFavoriteBookEntity;
import pl.bookshop.bookservice.entity.composedKey.SystemUserFavoriteBookId;

public interface SystemUserFavoriteBookRepository {

    boolean existsById(SystemUserFavoriteBookId id);

    SystemUserFavoriteBookEntity save(SystemUserFavoriteBookEntity entity);

    @Query(value = """
        SELECT COUNT(*)
        FROM system_user_favorite_book
        WHERE user_id = :userId
    """, nativeQuery = true)
    int countUserFavoriteBooks(@Param("userId") long userId);

    void deleteById(SystemUserFavoriteBookId id);

}
