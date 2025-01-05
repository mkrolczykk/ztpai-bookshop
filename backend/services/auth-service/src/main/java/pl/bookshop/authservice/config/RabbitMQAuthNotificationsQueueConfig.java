package pl.bookshop.authservice.config;

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

    public static final String RABBITMQ_QUEUE = "pl.bookshop.auth.notification.message";

    public static final String RABBITMQ_EXCHANGE = "authnotification_exchange";

    public static final String RABBITMQ_ROUTING_KEY = "authnotification_key";

    @Bean
    public Queue authNotificationsQueue(){
        return new Queue(RABBITMQ_QUEUE);
    }

    @Bean
    public TopicExchange authNotificationsExchange(){
        return new TopicExchange(RABBITMQ_EXCHANGE);
    }

    @Bean
    public Binding authNotificationsBinding() {
        return BindingBuilder
                .bind(authNotificationsQueue())
                .to(authNotificationsExchange())
                .with(RABBITMQ_ROUTING_KEY);
    }
}
