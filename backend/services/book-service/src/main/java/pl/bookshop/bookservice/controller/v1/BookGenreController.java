package pl.bookshop.bookservice.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bookshop.bookservice.dto.response.GenresResp;
import pl.bookshop.bookservice.service.BookGenreService;

import java.util.Set;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/genres")
public class BookGenreController {

    private final BookGenreService bookGenreService;

    @GetMapping
    public ResponseEntity<Set<GenresResp>> getAllAvailableGenres() {

        return ResponseEntity.ok(
                bookGenreService.getAllAvailableGenres());
    }

}
