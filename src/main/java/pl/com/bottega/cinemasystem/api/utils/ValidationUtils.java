package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.util.Collection;
import java.util.Set;

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

    public static void validateTicketKinds(Set<String> ticketKinds, Integer minAge) {
        if (filmIsAvailableForEveryone(minAge)) {
            requestShouldContainAllTicketTypes(ticketKinds);
        }
        if (minAgeIs16(minAge)) {
            requestShouldContainSchoolStudentAndRegularTicketTypes(ticketKinds, minAge);
        }
        if (minAgeIs18(minAge)) {
            requestShouldContainStudentAndRegularTicketTypes(ticketKinds);
        }
    }

    private static boolean filmIsAvailableForEveryone(Integer minAge) {
        return minAge == 0;
    }

    private static boolean minAgeIs16(Integer minAge) {
        return minAge >= 16;
    }

    private static boolean minAgeIs18(Integer minAge) {
        return minAge == 18;
    }

    private static void requestShouldContainAllTicketTypes(Set<String> ticketKinds) {
        if (!(ticketKinds.contains("children") &&
                ticketKinds.contains("school") &&
                ticketKinds.contains("student") &&
                ticketKinds.contains("regular"))) {
            throw new InvalidRequestException("More ticket kinds required to declare");
        }
    }

    private static void requestShouldContainSchoolStudentAndRegularTicketTypes(Set<String> ticketKinds, Integer minAge) {
        if (!(ticketKinds.contains("school") &&
                ticketKinds.contains("student") &&
                ticketKinds.contains("regular"))) {
            throw new InvalidRequestException("More ticket kinds required to declare");
        }
        if (ticketKinds.contains("children")) {
            throw new InvalidRequestException("Children ticket kind is not allowed " +
                    "when minimum age for movie is: " + minAge);
        }
    }

    private static void requestShouldContainStudentAndRegularTicketTypes(Set<String> ticketKinds) {
        if (!(ticketKinds.contains("student") &&
                ticketKinds.contains("regular"))) {
            throw new InvalidRequestException("More price types required to declare");
        }
        if (ticketKinds.contains("children") || ticketKinds.contains("school")) {
            throw new InvalidRequestException("Incorrect price types were declared");
        }
    }
}
