package pl.bookshop.authservice.clients;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitMQWelcomeMsgQueueProducer {

    public static final String RABBITMQ_QUEUE = "pl.bookshop.user.welcome.message";

    public static final String RABBITMQ_EXCHANGE = "welcomemessage_exchange";

    public static final String RABBITMQ_ROUTING_KEY = "welcomemessage_key";

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Object message) {
        log.info("Sending message {} to rabbitMQ queue {}", message.toString(), RABBITMQ_QUEUE);
        rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE, RABBITMQ_ROUTING_KEY, JSON.toJSONString(message));
    }
}
