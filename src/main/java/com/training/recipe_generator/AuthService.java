package com.training.recipe_generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Value("${get.recipe.api.password}")
    private String expectedPassword;

    public void validatePassword(String password) {
        if (!expectedPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }
    }
}
