package pl.bookshop.notificationsservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQAuthNotificationsQueueConfig {

    @Bean
    public Queue authNotificationsQueue() {
        return new Queue("pl.bookshop.auth.notification.message");
    }

    @Bean
    public TopicExchange authNotificationsExchange(){
        return new TopicExchange("authnotification_exchange");
    }

    @Bean
    public Binding authNotificationsBinding() {
        return BindingBuilder
                .bind(authNotificationsQueue())
                .to(authNotificationsExchange())
                .with("authnotification_key");
    }
}
