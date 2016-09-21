package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

public class ValidationUtils {

    public static void validateId(Long id, String errorMessage) {
        if (id == null || id <= 0) {
            throw new InvalidRequestException(errorMessage);
        }
    }

    public static void validateString(String string, String errorMessage) {
        if (string == null || string.trim().isEmpty())
            throw new InvalidRequestException(errorMessage);
    }
}
