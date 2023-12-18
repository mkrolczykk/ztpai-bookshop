package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.CurrencyEntity;
import pl.bookshop.bookservice.repository.facade.CurrencyRepository;

@Repository
interface SqlCurrencyRepository
        extends CurrencyRepository, JpaRepository<CurrencyEntity, Long> {}
