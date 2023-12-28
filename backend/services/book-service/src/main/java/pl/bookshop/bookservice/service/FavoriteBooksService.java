package pl.bookshop.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.dto.response.FavoritesBookActionResp;
import pl.bookshop.bookservice.dto.response.FavoritesBooksCountResp;
import pl.bookshop.bookservice.entity.SystemUserFavoriteBookEntity;
import pl.bookshop.bookservice.entity.composedKey.SystemUserFavoriteBookId;
import pl.bookshop.bookservice.exception.AlreadyExistsException;
import pl.bookshop.bookservice.exception.NotFoundException;
import pl.bookshop.bookservice.repository.facade.BookRepository;
import pl.bookshop.bookservice.repository.facade.SystemUserFavoriteBookRepository;
import pl.bookshop.bookservice.utils.FavoritesActionConstants;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FavoriteBooksService {

    private final BookRepository bookRepository;

    private final SystemUserFavoriteBookRepository favoriteBooksRepository;

    @Transactional
    public FavoritesBookActionResp addBookToFavorites(final Long requestorId, final long bookId) {

        SystemUserFavoriteBookId favoriteBookId =
                SystemUserFavoriteBookId.builder()
                        .userId(requestorId)
                        .bookId(bookId)
                        .build();

        if(!favoriteBooksRepository.existsById(favoriteBookId)) {
            if (bookRepository.existsById(bookId)) {
                favoriteBooksRepository.save(
                        SystemUserFavoriteBookEntity.builder()
                                .id(favoriteBookId)
                                .build());

                return FavoritesBookActionResp.builder()
                        .action(FavoritesActionConstants.ADD_TO_FAVORITES)
                        .message("Added to favorites!")
                        .build();
            } else
                throw new NotFoundException(String.format("No book with bookId '%s' available", bookId));
        } else
            throw new AlreadyExistsException("The book is already present in favorites");
    }

    public FavoritesBooksCountResp countUserFavoriteBooks(final Long requestorId) {

        return FavoritesBooksCountResp.builder()
                .action(FavoritesActionConstants.COUNT_FAVORITE_BOOKS)
                .result(favoriteBooksRepository.countUserFavoriteBooks(requestorId))
                .build();
    }

    public List<BookDto> getUserFavoriteBooks(final Long requestorId, final long limit) {
        return favoriteBooksRepository.getUserFavoriteBooks(requestorId, limit);
    }

    @Transactional
    public FavoritesBookActionResp deleteBookFromFavorites(final Long requestorId, final long bookId) {

        SystemUserFavoriteBookId favoriteBookId =
                SystemUserFavoriteBookId.builder()
                        .userId(requestorId)
                        .bookId(bookId)
                        .build();

        if(favoriteBooksRepository.existsById(favoriteBookId)) {
            favoriteBooksRepository.deleteById(favoriteBookId);
            return FavoritesBookActionResp.builder()
                    .action(FavoritesActionConstants.REMOVE_FROM_FAVORITES)
                    .message("Removed from favorites!")
                    .build();
        } else
            throw new NotFoundException(String.format("No book with bookId '%s' in favorites", bookId));
    }

}
