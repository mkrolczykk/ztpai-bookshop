package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.ShippingMethodEntity;
import pl.bookshop.bookservice.repository.facade.ShippingMethodEntityRepository;

@Repository
interface SqlShippingMethodEntityRepository
        extends ShippingMethodEntityRepository, JpaRepository<ShippingMethodEntity, Long> {}