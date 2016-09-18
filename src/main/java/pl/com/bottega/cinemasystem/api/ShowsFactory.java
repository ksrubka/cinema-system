package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ShowsFactory {

    public Collection<Show> getShows(Cinema cinema, Movie movie, CreateShowsRequest request) {
        Collection<LocalDateTime> dates = request.getShowDates();
        List<Show> shows = new ArrayList<>();
        dates.forEach(date -> shows.add(new Show(cinema, movie, date)));
        return shows;
    }

}
