package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.BookPriceEntity;

public interface BookPriceRepository {

    BookPriceEntity save(BookPriceEntity entity);

}
