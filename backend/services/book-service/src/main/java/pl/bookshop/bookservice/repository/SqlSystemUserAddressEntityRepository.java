package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.SystemUserAddressEntity;
import pl.bookshop.bookservice.repository.facade.SystemUserAddressEntityRepository;

@Repository
interface SqlSystemUserAddressEntityRepository
        extends SystemUserAddressEntityRepository, JpaRepository<SystemUserAddressEntity, Long> {}
