package com.training.recipe_generator;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt, int maxTokens) {
        var chatOptions = OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.7).maxTokens(maxTokens).build();
        var chatPrompt = new Prompt(prompt, chatOptions);
        return chatModel.call(chatPrompt).getResult().getOutput().getText();
    }
}
