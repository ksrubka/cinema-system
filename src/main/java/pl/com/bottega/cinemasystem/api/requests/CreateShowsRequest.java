package pl.com.bottega.cinemasystem.api.requests;

import pl.com.bottega.cinemasystem.api.dtos.CalendarDto;
import pl.com.bottega.cinemasystem.api.dtos.ManyShowsDto;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.api.strategies.CalendarBasedDatesCreatingStrategy;
import pl.com.bottega.cinemasystem.api.strategies.CalendarBasedValidationStrategy;
import pl.com.bottega.cinemasystem.api.strategies.DatesBasedValidationStrategy;
import pl.com.bottega.cinemasystem.api.strategies.ValidationStrategy;
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
        ValidationUtils.validateId(getCinemaId(),
                "Incorrect cinema id: " + getCinemaId());
    }

    private void validateMovieId() {
        ValidationUtils.validateId(getMovieId(),
                "Incorrect movie id: " + getMovieId());
    }

    private ValidationStrategy generateValidationStrategy() {
        if (getCalendar() == null) {
            return new DatesBasedValidationStrategy(getDates());
        } else {
            return new CalendarBasedValidationStrategy(getCalendar());
        }
    }

    public Collection<LocalDateTime> getShowDates() {
        if (getCalendar() == null) {
            return getDates();
        } else {
            return new CalendarBasedDatesCreatingStrategy(getCalendar()).generateShowDates();
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