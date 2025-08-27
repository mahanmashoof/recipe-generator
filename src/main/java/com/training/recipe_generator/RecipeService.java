package com.training.recipe_generator;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final ChatModel recipeModel;

    public RecipeService(ChatModel recipeModel) {
        this.recipeModel = recipeModel;
    }

    public String createRecipe(String ingredients, String dietType, int portions, String cuisine, int maxTokens) {
        var chatOptions = OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.7).maxTokens(maxTokens).build();
        String promptText = """
                Create a recipe with the following requirements:
                Ingredients: %s
                Diet type: %s
                Portions: %s
                Cuisine: %s
                        
                Please provide a recipe with simple and straightforward cooking instructions in a pointed list.
                """.formatted(ingredients, dietType, portions, cuisine);
        var chatPrompt = new Prompt(promptText, chatOptions);
        return recipeModel.call(chatPrompt).getResult().getOutput().getText();
    }
}
