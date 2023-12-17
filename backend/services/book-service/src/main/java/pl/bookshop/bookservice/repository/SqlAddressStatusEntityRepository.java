package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.AddressStatusEntity;
import pl.bookshop.bookservice.repository.facade.AddressStatusEntityRepository;

@Repository
interface SqlAddressStatusEntityRepository
        extends AddressStatusEntityRepository, JpaRepository<AddressStatusEntity, Long> {}
