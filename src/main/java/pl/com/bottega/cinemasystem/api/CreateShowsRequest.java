package pl.com.bottega.cinemasystem.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateShowsRequest {

    private ShowsDto shows;

    public void validate() {
        validateMovieId();
        generateValidationStrategy().validate();
    }

    private ValidationStrategy generateValidationStrategy() {
        if (shows.getCalendar() == null) {
            return new DatesBasedValidationStrategy(shows.getDates());
        } else {
            return new CalendarBasedValidationStrategy(shows.getCalendar());
        }
    }

    private void validateMovieId() {
        if (shows.getMovieId() == null || shows.getMovieId() <= 0) {
            throw new InvalidRequestException("Incorrect movie id");
        }
    }

    public List<Date> getShowDates() {
        return new ArrayList<>(generateStrategyCreatingShowDates().generateShowDates());
    }

    private DatesCreatingStrategy generateStrategyCreatingShowDates() {
        if (shows.getCalendar() == null) {
            return new StringsBasedDatesCreatingStrategy(shows.getDates());
        } else {
            return new CalendarBasedDatesCreatingStrategy(shows.getCalendar());
        }
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }

    public ShowsDto getShows() {
        return shows;
    }

    public void setShows(ShowsDto shows) {
        this.shows = shows;
    }
}