package com.training.recipe_generator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

    private final ChatService chatService;
    private final RecipeService recipeService;

    public GenAiController(ChatService chatService, RecipeService recipeService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt, @RequestParam(defaultValue = "100") int maxTokens){
        return chatService.getResponse(prompt, maxTokens);
    }

    //test api
    @GetMapping("/recipe")
    public String createRecipe(@RequestParam String ingredients, @RequestParam String dietType, @RequestParam int portions, @RequestParam String cuisine, @RequestParam(defaultValue = "1000") int maxTokens) {
        return recipeService.createRecipe(ingredients, dietType, portions, cuisine, maxTokens);
    }
}
