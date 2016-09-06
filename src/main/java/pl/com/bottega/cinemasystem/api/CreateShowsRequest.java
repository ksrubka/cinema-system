package pl.com.bottega.cinemasystem.api;

import java.util.Date;
import java.util.List;

public class CreateShowsRequest {

    private ShowsWrapper shows;

    public void validate() {
        shows.validate();
    }

    public Long getMovieId() {
        return shows.getMovieId();
    }

    public List<Date> getShowDates() {
        return shows.getShowDates();
    }

    public ShowsWrapper getShows() {
        return shows;
    }

    public void setShows(ShowsWrapper shows) {
        this.shows = shows;
    }
}
