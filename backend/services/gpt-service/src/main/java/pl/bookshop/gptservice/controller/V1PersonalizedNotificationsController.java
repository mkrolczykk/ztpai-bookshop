package pl.bookshop.gptservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bookshop.gptservice.dto.request.RegisteredUserInfoDto;
import pl.bookshop.gptservice.service.V1PersonalizedNotificationsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/notifications")
public class V1PersonalizedNotificationsController {

    private final V1PersonalizedNotificationsService notificationsService;

    @PostMapping(value = "/generate/welcomeMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateWelcomeMessage(@RequestBody RegisteredUserInfoDto dto) {
        return ResponseEntity.ok(notificationsService.generateWelcomeMessage(dto));
    }

}
