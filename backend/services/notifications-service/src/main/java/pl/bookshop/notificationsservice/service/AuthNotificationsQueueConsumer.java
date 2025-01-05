package pl.bookshop.notificationsservice.service;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import pl.bookshop.notificationsservice.common.NotificationType;
import pl.bookshop.notificationsservice.dto.response.AuthNotificationEvent;

import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthNotificationsQueueConsumer {

    @Value("${spring.mail.username}")
    private String serviceEmail;

    private final MailService mailService;

    @RabbitListener(queues = {"pl.bookshop.auth.notification.message"})
    public void consume(String message) {

        AuthNotificationEvent authNotificationEvent = parseRabbitMQMessage(message);

        if (Objects.nonNull(authNotificationEvent)) {
            log.info("Message event received: {}", authNotificationEvent.toString());

            String channel = authNotificationEvent.getChannel();
            String receiver = authNotificationEvent.getReceiver();
            String content = authNotificationEvent.getContent().trim();

            NotificationType notificationType = NotificationType.fromString(channel.trim().toUpperCase());
            switch (notificationType) {
                case EMAIL -> {

                    SimpleMailMessage emailMessage = new SimpleMailMessage();
                    String subject = "Service notification.";

                    emailMessage.setFrom(serviceEmail);
                    emailMessage.setTo(receiver);
                    emailMessage.setSubject(subject);
                    emailMessage.setText(content);

                    try {
                        mailService.sendEmail(emailMessage);
                        log.info(String.format("Notification for channel '%s' with receiver: '%s' has been successfully sent",
                                channel, receiver));
                    } catch(MailSendException e)  {
                        log.error(
                            "Failed to send notification for channel '{}' and receiver '{}', reason: '{}'",
                            channel,
                            receiver,
                            e.getMessage()
                        );
                    }
                }
                case TELEGRAM -> {
                    log.info("Telegram");
                }
                case PUSH -> {
                    log.info("Push");
                }
                case SMS -> {
                    log.info("SMS");
                }
                case WHATSAPP -> {
                    log.info("Whatsapp");
                }
                case UNKNOWN -> {
                    log.error("UNKNOWN CHANNEL");
                }
            }
        }
    }

    private AuthNotificationEvent parseRabbitMQMessage(String message) {
        try {
            return JSON.parseObject(message, AuthNotificationEvent.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

}
