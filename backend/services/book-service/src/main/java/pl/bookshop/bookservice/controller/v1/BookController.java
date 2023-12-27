package pl.bookshop.bookservice.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.EmployeeAuthority;
import pl.bookshop.auth.util.annotation.UserAuthority;
import pl.bookshop.bookservice.dto.TopSoldBooksDto;
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

    @UserAuthority
    @GetMapping("/bestsellers")
    public ResponseEntity<List<TopSoldBooksDto>> getTopSoldBooks(
            @RequestHeader(value = "currency", defaultValue = "USD") String currency,
            @RequestHeader(value = "limit", defaultValue = "10") Integer limit) {

        return ResponseEntity.ok(bookService.getTopSoldBooks(currency, limit));
    }

}
