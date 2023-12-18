package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.AddressStatusEntity;
import pl.bookshop.bookservice.repository.facade.AddressStatusRepository;

@Repository
interface SqlAddressStatusRepository
        extends AddressStatusRepository, JpaRepository<AddressStatusEntity, Long> {}
