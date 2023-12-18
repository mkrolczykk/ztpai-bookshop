package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.BookPriceEntity;
import pl.bookshop.bookservice.entity.composedKey.BookPriceId;
import pl.bookshop.bookservice.repository.facade.BookPriceRepository;

@Repository
interface SqlBookPriceRepository
        extends BookPriceRepository, JpaRepository<BookPriceEntity, BookPriceId> {}
