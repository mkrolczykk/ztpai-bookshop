package pl.bookshop.gptservice.service;

import pl.bookshop.gptservice.client.OpenAIClient;
import pl.bookshop.gptservice.client.OpenAIClientConfig;
import pl.bookshop.gptservice.dto.request.*;
import pl.bookshop.gptservice.dto.response.ChatGPTResponse;
import pl.bookshop.gptservice.dto.response.WhisperTranscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OpenAIClientService {

    private final OpenAIClient openAIClient;

    private final OpenAIClientConfig openAIClientConfig;

    private final static String ROLE_USER = "user";

    public ChatGPTResponse chat(ChatRequest chatRequest){
        Message message = Message.builder()
                .role(ROLE_USER)
                .content(chatRequest.getQuestion())
                .build();

        return openAIClient.chat(buildGPTRequest(message));
    }

    public WhisperTranscriptionResponse createTranscription(TranscriptionRequest transcriptionRequest){
        WhisperTranscriptionRequest whisperTranscriptionRequest = WhisperTranscriptionRequest.builder()
                .model(openAIClientConfig.getAudioModel())
                .file(transcriptionRequest.getFile())
                .build();
        return openAIClient.createTranscription(whisperTranscriptionRequest);
    }

    private ChatGPTRequest buildGPTRequest(Message message) {
        return ChatGPTRequest.builder()
                .model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message))
                .build();
    }

}
