package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UUIDValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String UUID_PATTERN =
    "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    public UUIDValidator() {
        pattern = Pattern.compile(UUID_PATTERN);
    }

    public void validate(final String uuid) {
        matcher = pattern.matcher(uuid);
        if (!matcher.matches()) {
            throw new InvalidRequestException("Invalid UUID code: " + uuid);
        }
    }
}
