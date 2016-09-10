package pl.com.bottega.cinemasystem.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ShowsDto {

    private Long movieId;
    private CalendarDto calendar;
    private Collection<String> dates;

    public List<Date> getShowDates() {
        return generateStrategyCreatingShowDates().generateShowDates();
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public CalendarDto getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarDto calendar) {
        this.calendar = calendar;
    }

    public Collection<String> getDates() {
        return dates;
    }

    public void setDates(Collection<String> dates) {
        this.dates = dates;
    }

    public void validate() {
        checkMovieId();
        generateStrategyCreatingShowDates().validate();
    }

    private void checkMovieId() {
        if (movieId < 0) {
            throw new InvalidRequestException("Incorrect movie id");
        }
    }

    private DatesCreatingStrategy generateStrategyCreatingShowDates() {
        if (calendar == null) {
            return new StringsBasedDatesCreatingStrategy(dates);
        }
        else {
            return new CalendarBasedDatesCreatingStrategy(calendar);
        }
    }
}