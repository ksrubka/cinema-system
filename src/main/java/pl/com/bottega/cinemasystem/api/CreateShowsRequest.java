package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

import java.time.LocalDateTime;
import java.util.Collection;

public class CreateShowsRequest {

    private ManyShowsDto shows;

    public void validate() {
        validateShows();
        validateCinemaId();
        validateMovieId();
        generateValidationStrategy().validate();
    }

    private void validateShows() {
        if (shows == null) {
            throw new InvalidRequestException("Shows dto is null");
        }
        if (bothWaysOfCreatingShowsAreDefined() || noWayOfCreatingShowsIsDefined()) {
            throw new InvalidRequestException("Wrong request: user should define one way of creating shows");
        }
    }

    private boolean bothWaysOfCreatingShowsAreDefined() {
        return shows.getDates() == null && shows.getCalendar() == null;
    }

    private boolean noWayOfCreatingShowsIsDefined() {
        return shows.getDates() != null && shows.getCalendar() != null;
    }

    private void validateCinemaId() {
        ValidationUtils.validateId(shows.getCinemaId(),
                "Incorrect cinema id: " + shows.getCinemaId());
    }

    private void validateMovieId() {
        ValidationUtils.validateId(shows.getMovieId(),
                "Incorrect movie id: " + shows.getMovieId());
    }

    private ValidationStrategy generateValidationStrategy() {
        if (shows.getCalendar() == null) {
            return new DatesBasedValidationStrategy(shows.getDates());
        } else {
            return new CalendarBasedValidationStrategy(shows.getCalendar());
        }
    }

    public Collection<LocalDateTime> getShowDates() {
        if (shows.getCalendar() == null) {
            return shows.getDates();
        } else {
            return new CalendarBasedDatesCreatingStrategy(shows.getCalendar()).generateShowDates();
        }
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }

    public CalendarDto getCalendar() {
        return shows.getCalendar();
    }

    public Collection<LocalDateTime> getDates() {
        return shows.getDates();
    }

    public ManyShowsDto getShows() {
        return shows;
    }

    public void setShows(ManyShowsDto shows) {
        this.shows = shows;
    }

    public void setCinemaId(Long cinemaId) {
        shows.setCinemaId(cinemaId);
    }

    public Long getCinemaId() {
        return shows.getCinemaId();
    }
}