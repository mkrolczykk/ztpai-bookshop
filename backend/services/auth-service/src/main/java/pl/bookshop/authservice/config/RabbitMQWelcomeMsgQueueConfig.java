package pl.bookshop.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQWelcomeMsgQueueConfig {

    public static final String RABBITMQ_QUEUE = "pl.bookshop.user.welcome.message";

    public static final String RABBITMQ_EXCHANGE = "welcomemessage_exchange";

    public static final String RABBITMQ_ROUTING_KEY = "welcomemessage_key";

    @Bean
    public Queue queue(){
        return new Queue(RABBITMQ_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(RABBITMQ_EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(RABBITMQ_ROUTING_KEY);
    }
}
