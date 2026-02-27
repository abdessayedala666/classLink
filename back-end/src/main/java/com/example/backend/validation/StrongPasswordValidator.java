package com.example.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            // At least 8 characters
            new LengthRule(8, 128),
            // At least one uppercase letter
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            // At least one lowercase letter
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            // At least one digit
            new CharacterRule(EnglishCharacterData.Digit, 1),
            // At least one special character
            new CharacterRule(EnglishCharacterData.Special, 1),
            // No whitespace
            new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        // Customize error message with validation details
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(", ", messages);
        
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation();

        return false;
    }
}
