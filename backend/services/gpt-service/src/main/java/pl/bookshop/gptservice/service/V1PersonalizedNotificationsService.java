package pl.bookshop.gptservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bookshop.gptservice.client.OpenAIClient;
import pl.bookshop.gptservice.client.OpenAIClientConfig;
import pl.bookshop.gptservice.dto.request.*;
import pl.bookshop.gptservice.dto.response.Choice;
import pl.bookshop.gptservice.exception.GPTServiceException;
import pl.bookshop.gptservice.gpttemplates.GPTNotificationsTemplates;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class V1PersonalizedNotificationsService {

    private final OpenAIClient openAIClient;

    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";

    public String generateWelcomeMessage(RegisteredUserInfoDto dto) {
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(GPTNotificationsTemplates
                        .getTemplateForWelcomeMessageQuery(
                                dto.getMessageLanguage(),
                                dto.getName(),
                                dto.getSurname()))
                .build();

        Message resp = openAIClient.chat(buildGPTRequest(message))
                .getChoices().stream()
                    .findFirst()
                    .map(Choice::getMessage)
                    .orElseThrow(() ->
                            new GPTServiceException(String.format("GPT service welcome message response timeout for user '%s %s'", dto.getName(), dto.getSurname())));

        return resp.getContent();
    }

    private ChatGPTRequest buildGPTRequest(Message message) {
        return ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();
    }

}
