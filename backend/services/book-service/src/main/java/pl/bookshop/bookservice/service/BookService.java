package pl.bookshop.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bookshop.bookservice.dto.response.BookDetailDto;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.dto.response.TopSoldBookDto;
import pl.bookshop.bookservice.dto.request.AddBookReq;
import pl.bookshop.bookservice.entity.*;
import pl.bookshop.bookservice.entity.composedKey.BookAuthorId;
import pl.bookshop.bookservice.entity.composedKey.BookPriceId;
import pl.bookshop.bookservice.exception.AlreadyExistsException;
import pl.bookshop.bookservice.exception.NotFoundException;
import pl.bookshop.bookservice.repository.facade.*;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    private final BookLanguageRepository bookLanguageRepository;

    private final BookGenreRepository bookGenreRepository;

    private final AuthorRepository authorRepository;

    private final BookAuthorRepository bookAuthorRepository;

    private final CurrencyRepository currencyRepository;

    private final BookPriceRepository bookPriceRepository;

    @Transactional
    public void addBook(final AddBookReq bookDto) {

        if (bookRepository.existsByTitle(bookDto.getTitle().toLowerCase())) {
            throw new AlreadyExistsException(String.format("Book with title '%s' already exists", bookDto.getTitle()));
        }

        long publisherId = publisherRepository
                .findByPublisherNameIgnoreCase(bookDto.getPublisher())
                .map(PublisherEntity::getPublisherId)
                .orElseGet(() ->
                        publisherRepository.save(PublisherEntity.builder()
                                        .publisherName(bookDto.getPublisher())
                                        .build())
                                .getPublisherId());

        long languageId = bookLanguageRepository.findByLanguageNameIgnoreCase(bookDto.getLanguage())
                .map(BookLanguageEntity::getLanguageId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Language '%s' is not supported", bookDto.getLanguage())));

        long genreId = bookGenreRepository.findByGenreIgnoreCase(bookDto.getGenre())
                .map(BookGenreEntity::getGenreId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Category '%s' does not exist", bookDto.getGenre())));

        long bookId =
                bookRepository.save(
                        bookDto.toBookEntity(
                                generateQuizSlug(bookDto.getTitle()),
                                languageId,
                                genreId,
                                publisherId))
                .getBookId();

        authorRepository.findByAuthorNameIgnoreCase(bookDto.getAuthor())
                .ifPresentOrElse(
                        authorEntity -> bookAuthorRepository.save(BookAuthorEntity.builder()
                                .id(BookAuthorId.builder()
                                        .authorId(authorEntity.getAuthorId())
                                        .bookId(bookId)
                                        .build())
                                .build()),
                        () -> {
                            long authorId = authorRepository.save(AuthorEntity.builder()
                                            .authorName(bookDto.getAuthor())
                                            .build())
                                    .getAuthorId();
                            bookAuthorRepository.save(BookAuthorEntity.builder()
                                    .id(BookAuthorId.builder()
                                            .authorId(authorId)
                                            .bookId(bookId)
                                            .build())
                                    .build());
                        });

        long currencyId = currencyRepository.findByCurrencyNameIgnoreCase(bookDto.getCurrency())
                .map(CurrencyEntity::getCurrencyId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Currency '%s' is not supported", bookDto.getCurrency())));

        bookPriceRepository.save(BookPriceEntity.builder()
                .id(BookPriceId.builder()
                        .bookId(bookId)
                        .currencyId(currencyId)
                        .build())
                .price(bookDto.getPrice())
                .build());
    }

    public BookDetailDto getBookById(Long bookId, String currency) {
        return bookRepository
                .getBookById(bookId, currency)
                .orElseThrow(() -> new NotFoundException("No book available with ID: " + bookId + " and currency: " + currency));
    }

    public List<BookDto> getBooksFromGivenCategory(String category, String currency) {
        return bookRepository.getBooksFromGivenCategory(category, currency);
    }

    public List<TopSoldBookDto> getTopSoldBooks(String currency, int limit) {
        return bookRepository.getTopSoldBooks(currency, limit);
    }

    private String generateQuizSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }

}
