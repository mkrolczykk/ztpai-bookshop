package pl.bookshop.authservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.bookshop.authservice.*", "pl.bookshop.auth.util.*"})
@OpenAPIDefinition(
	servers = {
		@Server(url = "http://localhost:8081/auth", description = "[LOCAL env] API Gateway auth service endpoint"),
		@Server(url = "http://localhost:8190/auth", description = "[LOCAL env] Auth service direct endpoint")})
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
