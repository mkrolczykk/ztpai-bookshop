package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.AddressEntity;
import pl.bookshop.bookservice.repository.facade.AddressRepository;

@Repository
interface SqlAddressRepository
        extends AddressRepository, JpaRepository<AddressEntity, Long> {}
