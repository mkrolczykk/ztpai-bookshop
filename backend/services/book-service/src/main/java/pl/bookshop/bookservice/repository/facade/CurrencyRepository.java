package pl.bookshop.bookservice.repository.facade;

import pl.bookshop.bookservice.entity.CurrencyEntity;

import java.util.Optional;

public interface CurrencyRepository {

    Optional<CurrencyEntity> getCurrencyEntityByShortcut(final String shortcut);

}
