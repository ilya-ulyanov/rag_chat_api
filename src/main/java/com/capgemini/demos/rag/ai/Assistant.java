package com.capgemini.demos.rag.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {
    @SystemMessage("You are helpful assistant. You respond only based on given CONTEXT and chat history. If you cannot find answer from the given CONTEXT and chat history - respond 'I do not have answer for that question.'")
    Result<String> chat(@MemoryId String chatId, @UserMessage String userMessage);
}
