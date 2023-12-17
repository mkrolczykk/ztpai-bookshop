package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.CurrencyEntity;
import pl.bookshop.bookservice.repository.facade.CurrencyEntityRepository;

@Repository
interface SqlCurrencyEntityRepository
        extends CurrencyEntityRepository, JpaRepository<CurrencyEntity, Long> {}
