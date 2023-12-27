package pl.bookshop.bookservice.controller.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.auth.util.annotation.AdminAuthority;
import pl.bookshop.auth.util.annotation.EmployeeAuthority;
import pl.bookshop.auth.util.annotation.UserAuthority;
import pl.bookshop.bookservice.dto.request.AddBookReq;
import pl.bookshop.bookservice.service.BookService;

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

}
