package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(final String hex) {
        matcher = pattern.matcher(hex);
        if (!matcher.matches()) {
            throw new InvalidRequestException("Invalid email address: " + hex);
        }
    }
}