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
public class RabbitMQWelcomeMessageQueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("pl.bookshop.user.welcome.message");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("welcomemessage_exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with("welcomemessage_key");
    }
}
