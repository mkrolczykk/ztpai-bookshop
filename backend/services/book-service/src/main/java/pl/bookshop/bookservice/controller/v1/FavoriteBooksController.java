package pl.bookshop.bookservice.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.UserAuthority;
import pl.bookshop.auth.util.utils.AuthenticationUtils;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.dto.response.FavoritesBookActionResp;
import pl.bookshop.bookservice.dto.response.FavoritesBooksCountResp;
import pl.bookshop.bookservice.service.FavoriteBooksService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/favorites")
public class FavoriteBooksController {

    private final FavoriteBooksService favoriteBooksService;

    @UserAuthority
    @PostMapping("/{bookId}")
    public ResponseEntity<FavoritesBookActionResp> addToFavorites(@PathVariable long bookId) {

        return ResponseEntity.ok(
                favoriteBooksService.addBookToFavorites(AuthenticationUtils.getUserId(), bookId));
    }

    @UserAuthority
    @GetMapping("/count")
    public ResponseEntity<FavoritesBooksCountResp> getUserFavoriteBooksNumber() {

        return ResponseEntity.ok(
                favoriteBooksService.countUserFavoriteBooks(AuthenticationUtils.getUserId()));
    }

    @UserAuthority
    @GetMapping
    public ResponseEntity<List<BookDto>> getUserFavoriteBooks(
            @RequestHeader(value = "limit", defaultValue = "2147483647") int limit) {

        return ResponseEntity.ok(
                favoriteBooksService.getUserFavoriteBooks(AuthenticationUtils.getUserId(), limit));
    }

    @UserAuthority
    @DeleteMapping("/{bookId}")
    public ResponseEntity<FavoritesBookActionResp> deleteFromFavorites(@PathVariable long bookId) {

        return ResponseEntity.ok(
                favoriteBooksService.deleteBookFromFavorites(AuthenticationUtils.getUserId(), bookId));
    }

}
