package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.util.Collection;

public class ValidationUtils {

    public static void validateInteger(Integer integer, String errorMessage) {
        if (integer == null || integer <= 0) {
            throw new InvalidRequestException(errorMessage);
        }
    }

    public static void validateId(Long id, String errorMessage) {
        if (id == null || id <= 0) {
            throw new InvalidRequestException(errorMessage);
        }
    }

    public static void validateString(String string, String errorMessage) {
        if (string == null || string.trim().isEmpty())
            throw new InvalidRequestException(errorMessage);
    }

    public static void validateCollectionOfStrings(Collection<String> validations, String msg) {
        if (validations == null || validations.isEmpty()) {
            throw new InvalidRequestException(msg);
        }
        validations.forEach(actor -> {
            if (actor.trim().isEmpty() || actor == null) {
                throw new InvalidRequestException(msg);
            }
        });
    }
}
