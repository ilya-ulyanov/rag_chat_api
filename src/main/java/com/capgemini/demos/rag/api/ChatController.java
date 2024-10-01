package com.capgemini.demos.rag.api;

import com.capgemini.demos.rag.ai.Assistant;
import com.capgemini.demos.rag.dto.AssistantResponse;
import com.capgemini.demos.rag.dto.PromptRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final Assistant assistant;

    @PostMapping(value = "chat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssistantResponse> getResponse(@RequestBody PromptRequest prompt) {
        var response = assistant.chat(prompt.getChatId(), prompt.getMessage());
        var result = new AssistantResponse();
        result.setResponse(response.content());
        return ResponseEntity.ok(result);
    }
}
