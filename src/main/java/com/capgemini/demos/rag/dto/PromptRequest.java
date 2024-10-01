package com.capgemini.demos.rag.dto;

import lombok.Data;

@Data
public class PromptRequest {
    private String chatId;
    private String message;
}
