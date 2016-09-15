package pl.com.bottega.cinemasystem.api;

import java.util.Date;
import java.util.List;

public class CalendarBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private CalendarDto calendar;

    public CalendarBasedDatesCreatingStrategy(CalendarDto calendar) {
        this.calendar = calendar;
    }

    @Override
    public void validate() {

    }

    @Override
    public List<Date> generateShowDates() {
        return null;
    }
}
