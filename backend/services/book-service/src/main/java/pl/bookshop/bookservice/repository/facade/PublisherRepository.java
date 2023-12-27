package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.PublisherEntity;

import java.util.Optional;

public interface PublisherRepository {

    PublisherEntity getPublisherEntityByPublisherName(String name);

    Optional<PublisherEntity> findByPublisherNameIgnoreCase(String name);

    PublisherEntity save(PublisherEntity entity);

    boolean existsByPublisherName(String name);

}
