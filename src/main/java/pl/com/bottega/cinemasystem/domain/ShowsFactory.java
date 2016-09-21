package pl.com.bottega.cinemasystem.domain;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.CreateShowsRequest;
import pl.com.bottega.cinemasystem.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ShowsFactory {

    public static Collection<Show> createShows(Cinema cinema, Movie movie, CreateShowsRequest request) {
        Collection<LocalDateTime> dates = request.getShowDates();
        List<Show> shows = new ArrayList<>();
        dates.forEach(date -> shows.add(new Show(cinema, movie, date)));
        return shows;
    }
}
