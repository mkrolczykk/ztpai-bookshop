package pl.bookshop.notificationsservice.client;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;
import pl.bookshop.auth.util.utils.AuthenticationUtils;

@Configuration
@ConfigurationProperties
@Indexed
@Data
@Slf4j
public class GPTServiceClientConfig {

    @Value("${gpt-service.http-client.read-timeout}")
    private int readTimeout;

    @Value("${gpt-service.http-client.connect-timeout}")
    private int connectTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(getConnectTimeout(), getReadTimeout());
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return request -> request.header("Authorization", "Bearer " + AuthenticationUtils.getUserId());
    }
}
