package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.CountryEntity;
import pl.bookshop.bookservice.repository.facade.CountryEntityRepository;

@Repository
interface SqlCountryEntityRepository
        extends CountryEntityRepository, JpaRepository<CountryEntity, Long> {}
