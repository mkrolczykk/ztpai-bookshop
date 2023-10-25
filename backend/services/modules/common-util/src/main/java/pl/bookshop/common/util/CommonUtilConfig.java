package pl.bookshop.common.util;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"pl.bookshop.common.util.*"})
@EnableJpaRepositories(basePackages = {"pl.bookshop.common.util.*"})
@EntityScan(basePackages = {"pl.bookshop.common.util.*"})
public class CommonUtilConfig {}
