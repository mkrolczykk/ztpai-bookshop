package pl.bookshop.gptservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"pl.bookshop.gptservice.*", "pl.bookshop.auth.util.*"})
@EntityScan(basePackages = {"pl.bookshop.gptservice.*", "pl.bookshop.auth.util.*"})
@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8081/gptclient", description = "[LOCAL env] API Gateway GPT service endpoint"),
				@Server(url = "http://localhost:8490/gptclient", description = "[LOCAL env] GPT service direct endpoint (Default: DISABLED)")})
public class GPTServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GPTServiceApplication.class, args);
	}

}
