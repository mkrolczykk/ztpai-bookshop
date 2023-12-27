package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.AuthorEntity;

import java.util.Optional;

public interface AuthorRepository {

    Optional<AuthorEntity> findByAuthorNameIgnoreCase(String name);

    boolean existsByAuthorName(String name);

    AuthorEntity save(AuthorEntity entity);

}
