package pl.bookshop.auth.util;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"pl.bookshop.auth.util.*"})
@EnableJpaRepositories(basePackages = {"pl.bookshop.auth.util.*"})
@EntityScan(basePackages = {"pl.bookshop.auth.util.*"})
public class AuthUtilConfig {}
