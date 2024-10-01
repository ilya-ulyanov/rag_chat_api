package com.capgemini.demos.rag.dto;

import lombok.Data;

@Data
public class PromptRequestMessage {
    private String role;
    private String content;
}
