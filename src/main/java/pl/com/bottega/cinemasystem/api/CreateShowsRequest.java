package pl.com.bottega.cinemasystem.api;

import java.util.Date;
import java.util.List;

public class CreateShowsRequest {

    private ShowsDto shows;

    public void validate() {
        shows.validate();
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }

    public List<Date> getShowDates() {
        return shows.getShowDates();
    }

    public ShowsDto getShows() {
        return shows;
    }

    public void setShows(ShowsDto shows) {
        this.shows = shows;
    }
}
