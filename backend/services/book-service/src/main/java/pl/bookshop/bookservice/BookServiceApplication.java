package pl.bookshop.bookservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"pl.bookshop.bookservice.*", "pl.bookshop.auth.util.*"})
@EntityScan(basePackages = {"pl.bookshop.bookservice.*", "pl.bookshop.auth.util.*"})
@OpenAPIDefinition(
	servers = {
		@Server(url = "http://localhost:8081/bookapp", description = "[LOCAL env] API Gateway Bookshop service endpoint"),
		@Server(url = "http://localhost:8290/bookapp", description = "[LOCAL env] Bookshop service direct endpoint (Default: DISABLED)")})
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
