package pl.com.bottega.cinemasystem.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringsBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private Collection<String> stringDates;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");

    public StringsBasedDatesCreatingStrategy(Collection<String> dates) {
        this.stringDates = dates;
    }

    @Override
    public void validate() {
        checkIfDatesAreSpecified();
        checkIfDatesAreCorrect();
        checkIfDatesHasNotPassed();
    }

    private void checkIfDatesAreSpecified() {
        if (stringDates == null) {
            throw new InvalidRequestException("Dates not specified");
        }
    }

    private void checkIfDatesAreCorrect() {
        stringDates.forEach(date -> parseDate(date));
    }

    private void checkIfDatesHasNotPassed() {
        Date now = new Date();
        stringDates.forEach(stringDate -> {
            Date date = parseDate(stringDate);
            checkIfDatePassed(now, date);
        });
    }

    private Date parseDate(String stringDate) {
        Date date;
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Incorrect stringDate format");
        }
        return date;
    }

    private void checkIfDatePassed(Date now, Date date) {
        if (now.compareTo(date) >= 0) {
            throw new InvalidRequestException("Given date has already passed: " + date);
        }
    }

    @Override
    public Set<Date> generateShowDates() {
        Set<Date> dates = new TreeSet<>();
        stringDates.forEach(date ->
                dates.add(parseDate(date)));
        return dates;
    }
}
