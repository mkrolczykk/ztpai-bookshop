package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.AuthorEntity;
import pl.bookshop.bookservice.repository.facade.AuthorRepository;

@Repository
interface SqlAuthorRepository
        extends AuthorRepository, JpaRepository<AuthorEntity, Long> {}
