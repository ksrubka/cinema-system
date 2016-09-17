package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.util.Collection;
import java.util.Date;

class DatesBasedValidationStrategy implements ValidationStrategy {

    private Collection<String> stringDates;

    DatesBasedValidationStrategy(Collection<String> dates) {
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
        stringDates.forEach(date -> DateUtil.validate(date));
    }

    private void checkIfDatesHasNotPassed() {
        stringDates.forEach(stringDate -> {
            Date date = DateUtil.parseDate(stringDate);
            checkIfDatePassed(date);
        });
    }

    private void checkIfDatePassed(Date date) {
        if (dateHasPassed(date)) {
            throw new InvalidRequestException("Given date has already passed: " + date);
        }
    }

    private boolean dateHasPassed(Date date) {
        Date now = new Date();
        return now.compareTo(date) >= 0;
    }
}
