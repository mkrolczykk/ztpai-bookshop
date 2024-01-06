package pl.bookshop.notificationsservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"pl.bookshop.notificationsservice.*", "pl.bookshop.auth.util.*"})
@EntityScan(basePackages = {"pl.bookshop.notificationsservice.*", "pl.bookshop.auth.util.*"})
@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8081/notifications", description = "[LOCAL env] API Gateway Notifications service endpoint"),
				@Server(url = "http://localhost:8390/notifications", description = "[LOCAL env] Notifications service direct endpoint (Default: DISABLED)")})
public class NotificationsServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsServiceApp.class, args);
	}

}
