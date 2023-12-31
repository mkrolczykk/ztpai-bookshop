package pl.bookshop.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bookshop.bookservice.entity.PublisherEntity;
import pl.bookshop.bookservice.repository.facade.PublisherRepository;

@Repository
interface SqlPublisherRepository
        extends PublisherRepository, JpaRepository<PublisherEntity, Long> {}
