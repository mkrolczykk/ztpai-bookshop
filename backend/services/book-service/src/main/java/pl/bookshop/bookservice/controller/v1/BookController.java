package pl.bookshop.bookservice.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.EmployeeAuthority;
import pl.bookshop.auth.util.annotation.UserAuthority;
import pl.bookshop.bookservice.dto.response.BookDetailDto;
import pl.bookshop.bookservice.dto.response.BookDto;
import pl.bookshop.bookservice.dto.response.TopSoldBookDto;
import pl.bookshop.bookservice.dto.request.AddBookReq;
import pl.bookshop.bookservice.service.BookService;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/books")
class BookController {

    private final BookService bookService;

    @EmployeeAuthority
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addBook(@RequestBody @Valid AddBookReq bookDto) {

        bookService.addBook(bookDto);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<BookDto>> getBooksFromGivenCategory(
            @PathVariable String category,
            @RequestHeader(value = "currency", defaultValue = "USD") String currency) {

        return ResponseEntity.ok(bookService.getBooksFromGivenCategory(category, currency));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> getBooksByTitleOrAuthor(
            @RequestParam("searchkey") String searchKey,
            @RequestHeader(value = "currency", defaultValue = "USD") String currency) {

        return ResponseEntity.ok(bookService.getBooksByTitleOrAuthor(searchKey, currency));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDetailDto> getBookById(
            @PathVariable Long bookId,
            @RequestHeader(value = "currency", defaultValue = "USD") String currency) {

        return ResponseEntity.ok(bookService.getBookById(bookId, currency));
    }

    @GetMapping("/bestsellers")
    public ResponseEntity<List<TopSoldBookDto>> getTopSoldBooks(
            @RequestHeader(value = "currency", defaultValue = "USD") String currency,
            @RequestHeader(value = "limit", defaultValue = "10") Integer limit) {

        return ResponseEntity.ok(bookService.getTopSoldBooks(currency, limit));
    }

    @GetMapping("/recents")
    public ResponseEntity<List<BookDto>> getRecentlyAddedBooks(
            @RequestHeader(value = "currency", defaultValue = "USD") String currency,
            @RequestHeader(value = "limit", defaultValue = "20") Integer limit) {

        return ResponseEntity.ok(bookService.getRecentlyAddedBooks(currency, limit));
    }

}
