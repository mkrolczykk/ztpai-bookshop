package pl.bookshop.notificationsservice.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.bookshop.notificationsservice.dto.request.GenerateWelcomeMsgReq;

@FeignClient(
        name = "qpt-service",
        url = "${gpt-service.urls.base-url}",
        configuration = GPTServiceClientConfig.class
)
public interface GPTServiceClient {

    @PostMapping(
            value = "${gpt-service.urls.generate-welcome-message}",
            headers = {"Content-Type=application/json"})
    ResponseEntity<String> generateWelcomeMessage(@RequestBody @Valid GenerateWelcomeMsgReq body);
}
