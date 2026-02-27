package com.example.backend.utils;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RandomPasswordGenerator {

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*?";

    /**
     * Generates a random strong password with at least 16 characters.
     * The password will contain:
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character from !@#$%^&*?
     *
     * @return A randomly generated strong password
     */
    public String generatePassword() {
        return generatePassword(16);
    }

    /**
     * Generates a random strong password with the specified length.
     * The password will contain:
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character from !@#$%^&*?
     *
     * @param length The desired password length (minimum 16)
     * @return A randomly generated strong password
     */
    public String generatePassword(int length) {
        if (length < 16) {
            length = 16;
        }

        PasswordGenerator generator = new PasswordGenerator();

        // Define custom special characters
        CharacterData specialChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return "INSUFFICIENT_SPECIAL";
            }

            @Override
            public String getCharacters() {
                return SPECIAL_CHARACTERS;
            }
        };

        // Define character rules
        List<CharacterRule> rules = Arrays.asList(
            // At least 4 uppercase letters
            new CharacterRule(EnglishCharacterData.UpperCase, 4),
            // At least 4 lowercase letters
            new CharacterRule(EnglishCharacterData.LowerCase, 4),
            // At least 4 digits
            new CharacterRule(EnglishCharacterData.Digit, 4),
            // At least 2 special characters from !@#$%^&*?
            new CharacterRule(specialChars, 2)
        );

        return generator.generatePassword(length, rules);
    }

    /**
     * Generates a random strong password with the specified length and minimum special characters.
     *
     * @param length The desired password length (minimum 16)
     * @param minSpecialChars Minimum number of special characters
     * @return A randomly generated strong password
     */
    public String generatePassword(int length, int minSpecialChars) {
        if (length < 16) {
            length = 16;
        }
        if (minSpecialChars < 1) {
            minSpecialChars = 1;
        }

        PasswordGenerator generator = new PasswordGenerator();

        // Define custom special characters
        CharacterData specialChars = new CharacterData() {
            @Override
            public String getErrorCode() {
                return "INSUFFICIENT_SPECIAL";
            }

            @Override
            public String getCharacters() {
                return SPECIAL_CHARACTERS;
            }
        };

        // Calculate remaining characters for other types
        int remaining = length - minSpecialChars;
        int perType = remaining / 3;

        // Define character rules
        List<CharacterRule> rules = Arrays.asList(
            new CharacterRule(EnglishCharacterData.UpperCase, perType),
            new CharacterRule(EnglishCharacterData.LowerCase, perType),
            new CharacterRule(EnglishCharacterData.Digit, perType),
            new CharacterRule(specialChars, minSpecialChars)
        );

        return generator.generatePassword(length, rules);
    }
}
