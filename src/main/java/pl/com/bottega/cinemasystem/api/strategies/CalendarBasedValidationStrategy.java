package pl.com.bottega.cinemasystem.api.strategies;

import pl.com.bottega.cinemasystem.api.dtos.CalendarDto;
import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.time.LocalTime;
import java.util.Collection;

public class CalendarBasedValidationStrategy implements ValidationStrategy {

    private CalendarDto calendar;

    public CalendarBasedValidationStrategy(CalendarDto calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate() {
        validateDates();
        validateHours();
    }

    private void validateDates() {
        DateUtil.validateDate(calendar.getFromDate());
        DateUtil.validateDate(calendar.getUntilDate());
    }

    private void validateHours() {
        Collection<LocalTime> hours = calendar.getHours();
        hours.forEach(hour -> DateUtil.validateTime(hour));
    }
}
