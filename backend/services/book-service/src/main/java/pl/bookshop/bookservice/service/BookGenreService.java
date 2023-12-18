package pl.bookshop.bookservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.bookshop.bookservice.dto.response.GenresResp;
import pl.bookshop.bookservice.repository.facade.BookGenreRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookGenreService {

    private final BookGenreRepository bookGenreRepository;

    public Set<GenresResp> getAllAvailableGenres() {
        return bookGenreRepository
                .findAll()
                .stream()
                .map(GenresResp::toGenresResp)
                .collect(Collectors.toSet());
    }

}
