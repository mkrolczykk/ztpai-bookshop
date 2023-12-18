package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.SystemUserAddressEntity;
import pl.bookshop.bookservice.entity.composedKey.SystemUserAddressId;
import pl.bookshop.bookservice.repository.facade.SystemUserAddressRepository;

@Repository
interface SqlSystemUserAddressRepository
        extends SystemUserAddressRepository, JpaRepository<SystemUserAddressEntity, SystemUserAddressId> {}
