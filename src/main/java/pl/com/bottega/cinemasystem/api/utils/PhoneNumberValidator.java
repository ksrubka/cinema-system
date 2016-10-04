package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PHONE_NR_PATTERN =
            "\\d{3}-\\d{3}-\\d{3}";

    public PhoneNumberValidator() {
        pattern = Pattern.compile(PHONE_NR_PATTERN);
    }

    public void validate(final String hex) {
        matcher = pattern.matcher(hex);
        if (!matcher.matches()) {
            throw new InvalidRequestException("Invalid phone number: " + hex);
        }
    }
}
