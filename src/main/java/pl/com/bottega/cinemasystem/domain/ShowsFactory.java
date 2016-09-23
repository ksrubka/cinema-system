package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.CreateShowsRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowsFactory {

    public static Collection<Show> createShows(Cinema cinema, Movie movie, CreateShowsRequest request) {
        Collection<LocalDateTime> dates = request.getShowDates();
        List<Show> shows = new ArrayList<>();
        dates.forEach(date -> shows.add(new Show(cinema, movie, date.toLocalDate(), date.toLocalTime())));
        return shows;
    }
}
