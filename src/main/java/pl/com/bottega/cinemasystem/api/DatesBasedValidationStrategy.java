package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

class DatesBasedValidationStrategy implements ValidationStrategy {

    private Collection<LocalDateTime> dates;

    DatesBasedValidationStrategy(Collection<LocalDateTime> dates) {
        this.dates = dates;
    }

    @Override
    public void validate() {
        checkIfDatesAreSpecified();
        checkIfDatesAreCorrect();
        DateUtil.checkIfDatesHasNotPassed(dates);
    }

    private void checkIfDatesAreSpecified() {
        if (dates == null) {
            throw new InvalidRequestException("Dates not specified");
        }
    }

    private void checkIfDatesAreCorrect() {
        dates.forEach(date -> validate(date));
    }

    private void validate(LocalDateTime dateAndTime) {
        LocalDate date = dateAndTime.toLocalDate();
        LocalTime time = dateAndTime.toLocalTime();
        DateUtil.validateDate(date);
        DateUtil.validateTime(time);
    }
}
