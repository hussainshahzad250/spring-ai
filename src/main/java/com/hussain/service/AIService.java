package com.hussain.service;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AIService {

    private final ChatClient chatClient;

    public String getPromptResult(String promptMessage) {
        return chatClient.prompt(promptMessage).call().content();
    }
}
