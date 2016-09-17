package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.util.Collection;

class CalendarBasedValidationStrategy implements ValidationStrategy {

    private CalendarDto calendar;

    CalendarBasedValidationStrategy(CalendarDto calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate() {
        checkDates();
        checkHours();
        checkWeekdays();
    }

    private void checkDates() {
        DateUtil.validate(calendar.getFromDate());
        DateUtil.validate(calendar.getUntilDate());
    }

    private void checkHours() {
        Collection<String> hours = calendar.getHours();
        hours.forEach(hour -> DateUtil.checkHourAndMinuteInTime(hour));
    }

    private void checkWeekdays() {
        try {
            calendar.getWeekDays().forEach(day -> Weekday.valueOf(day));
        } catch (Exception ex) {
            throw new InvalidRequestException("Incorrect weekday");
        }
    }
}
