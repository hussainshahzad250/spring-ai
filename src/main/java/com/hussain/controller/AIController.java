package com.hussain.controller;

import com.hussain.service.AIService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AIController {

    private final AIService aiService;

    @GetMapping("/hello/{message}")
    public String hello(@PathVariable String message) {
        return message;
    }

    @GetMapping("/prompt/{promptMessage}")
    public String helloFromAI(@PathVariable String promptMessage) {
        return aiService.getPromptResult(promptMessage);
    }


}
