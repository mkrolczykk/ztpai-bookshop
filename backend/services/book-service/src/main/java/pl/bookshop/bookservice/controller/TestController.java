package pl.bookshop.bookservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bookshop.auth.util.annotation.UserAuthority;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TestController {

    @UserAuthority
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
