package com.hussain.controller;

import com.hussain.service.AIService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/hello")
    public String helloFromSpringBoot() {
        return "Welcome to Spring Boot Application!!";
    }

    @GetMapping("/chatPrompt/{promptMessage}")
    public String helloFromChatPrompt(@PathVariable String promptMessage) {
        return aiService.getChatPromptResult(promptMessage);
    }

    @GetMapping("/imagePrompt/{promptMessage}")
    public String helloFromImagePrompt(@PathVariable String promptMessage) {
        return aiService.getImagePromptResult(promptMessage);
    }

}
