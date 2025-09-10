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

    public String createRecipe(String ingredients, String dietType, int portions, String cuisine, int maxTokens, String password) {
        var chatOptions = OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.7).maxTokens(maxTokens).build();
        String promptText = """
                I need a recipe with the following requirements:
                Ingredients: %s
                Diet type: %s
                Portions: %s
                Cuisine: %s
                
                The recipe should match the ingredients, but doesn't necessarily need to consist only of them. You are free to add more stuff if it results in a better recipe that matches the desired Diet type.
                Try to find the recipe online first. If difficult, you can create one.
                Please provide a recipe with simple and straightforward cooking instructions in json format as follows: title, ingredients: {title, quantity, unitOfMeasurement}, steps without numbers.
                """.formatted(ingredients, dietType, portions, cuisine);
        var chatPrompt = new Prompt(promptText, chatOptions);
        return recipeModel.call(chatPrompt).getResult().getOutput().getText();
    }
}
