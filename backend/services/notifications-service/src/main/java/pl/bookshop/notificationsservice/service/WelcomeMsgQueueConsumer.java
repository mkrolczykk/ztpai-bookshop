package pl.bookshop.notificationsservice.service;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import pl.bookshop.notificationsservice.client.GPTServiceClient;
import pl.bookshop.notificationsservice.dto.request.GenerateWelcomeMsgReq;
import pl.bookshop.notificationsservice.dto.response.UserRegisterSuccessEvent;
import pl.bookshop.notificationsservice.exception.PersonalizedMessageGenerationException;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WelcomeMsgQueueConsumer {

    @Value("${spring.mail.username}")
    private String serviceEmail;

    private final GPTServiceClient gptServiceClient;

    private final MailService mailService;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"pl.bookshop.user.welcome.message"})
    public void consume(String message) {

        UserRegisterSuccessEvent userInfo = parseRabbitMQMessage(message);

        if (Objects.nonNull(userInfo)) {
            log.info("Message event received: {}", userInfo.toString());

            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(serviceEmail);
            emailMessage.setTo(userInfo.getEmail());

            String language = userInfo.getMessageLanguage().trim().toLowerCase();
            String subject = switch (language) {
                case "polish" -> "Witamy w Naszej Społeczności! \uD83D\uDE80";
                case "spanish" -> "Bienvenido a Nuestra Comunidad! \uD83D\uDE80";
                case "french" -> "Bienvenue dans Notre Communauté! \uD83D\uDE80";
                case "german" -> "Willkommen in unserer Gemeinschaft! \uD83D\uDE80";
                default -> "Welcome to Our Community! \uD83D\uDE80";
            };

            ResponseEntity<String> personalizedMessageResp =
                    gptServiceClient.generateWelcomeMessage(
                            GenerateWelcomeMsgReq.builder()
                                    .messageLanguage(language)
                                    .name(userInfo.getName())
                                    .surname(userInfo.getSurname())
                                    .username(userInfo.getUsername())
                                    .build());

            if (personalizedMessageResp.getStatusCode() == HttpStatus.OK) {
                emailMessage.setSubject(subject);
                JsonNode jsonNode =
                        parseJson(personalizedMessageResp.getBody()).orElseThrow(() ->
                                new PersonalizedMessageGenerationException(
                                        String.format("Welcome message for user '%s %s' with email: '%s' generation fail. Please try again in a while",
                                                userInfo.getName(), userInfo.getSurname(), userInfo.getEmail())));
                emailMessage.setText(jsonNode.get("message").asText());
            } else {    // if gpt-service API response fail
                emailMessage.setSubject(subject);
                switch (language) {
                    case "polish" -> emailMessage.setText(String.format("Dzień dobry, %s! Jesteśmy zachwyceni, że dołączyłeś do naszej społeczności. Twoja podróż z nami dopiero się zaczyna, a my jesteśmy tutaj, aby ją uczynić niezwykłą. Śmiało eksploruj, nawiązuj kontakty i odkrywaj wszystkie niesamowite rzeczy, które mamy do zaoferowania.", userInfo.getName()));
                    case "spanish" -> emailMessage.setText(String.format("¡Hola y bienvenido, %s! Estamos encantados de que te hayas unido a nuestra comunidad. Tu viaje con nosotros acaba de comenzar y estamos aquí para que sea extraordinario. Siéntete libre de explorar, conectarte y descubrir todas las cosas increíbles que tenemos para ofrecer.", userInfo.getName()));
                    case "french" -> emailMessage.setText(String.format("Bonjour et bienvenue, %s! Nous sommes ravis que vous ayez rejoint notre communauté. Votre voyage avec nous ne fait que commencer, et nous sommes là pour le rendre extraordinaire. N'hésitez pas à explorer, à vous connecter et à découvrir toutes les choses incroyables que nous avons à offrir.", userInfo.getName()));
                    case "german" -> emailMessage.setText(String.format("Hallo und herzlich willkommen, %s! Wir freuen uns, dass Sie sich unserer Gemeinschaft angeschlossen haben. Ihre Reise mit uns beginnt gerade erst, und wir sind hier, um sie außergewöhnlich zu gestalten. Fühlen Sie sich frei zu erkunden, sich zu vernetzen und all die erstaunlichen Dinge zu entdecken, die wir zu bieten haben.", userInfo.getName()));
                    default -> emailMessage.setText(String.format("Hello and welcome, %s! We're thrilled to have you join our community. Your journey with us is just beginning, and we're here to make it extraordinary. Feel free to explore, connect, and discover all the amazing things we have to offer.", userInfo.getName()));
                }
            }
            mailService.sendEmail(emailMessage);
            log.info(String.format("Welcome message for user '%s %s' with email: '%s' has been successfully sent",
                    userInfo.getName(), userInfo.getSurname(), userInfo.getEmail()));
        }
    }

    private UserRegisterSuccessEvent parseRabbitMQMessage(String message) {
        try {
            return JSON.parseObject(message, UserRegisterSuccessEvent.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    private Optional<JsonNode> parseJson(String jsonString) {
        try {
            return Optional.of(objectMapper.readTree(jsonString));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
