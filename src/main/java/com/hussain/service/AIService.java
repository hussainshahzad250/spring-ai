package com.hussain.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AIService {

    private final ChatClient chatClient;
    private final OpenAiImageModel openAiImageModel;

    public String getChatPromptResult(String promptMessage) {
        return chatClient.prompt(promptMessage).call().content();
    }

    public String getImagePromptResult(String promptMessage) {
        ImagePrompt imagePrompt = new ImagePrompt(promptMessage);
        ImageResponse response = openAiImageModel.call(imagePrompt);
        System.out.println("Metadata : " + response.getMetadata());
        return response.getResult().getOutput().getUrl();

    }
}
