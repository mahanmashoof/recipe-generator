package com.training.recipe_generator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

    private final RecipeService recipeService;
    private final AuthService authService;

    public GenAiController(RecipeService recipeService, AuthService authService) {
        this.recipeService = recipeService;
        this.authService = authService;
    }

    @GetMapping("/recipe")
    public String createRecipe(@RequestParam String ingredients,
                               @RequestParam String dietType,
                               @RequestParam int portions,
                               @RequestParam String cuisine,
                               @RequestParam(defaultValue = "1000") int maxTokens,
                               @RequestHeader("password") String password) {
        authService.validatePassword(password);
        return recipeService.createRecipe(ingredients, dietType, portions, cuisine, maxTokens, password);
    }
}
