package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ShowsFactory {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    public List<Show> getShows(Long cinemaId, CreateShowsRequest createShowsRequest) {
        Cinema cinema = cinemaRepository.load(cinemaId);
        Movie movie = movieRepository.load(createShowsRequest.getMovieId());
        List<Date> dates = createShowsRequest.getShowDates();
        List<Show> shows = new ArrayList<>();
        dates.forEach(date -> shows.add(new Show(cinema, movie, date)));
        return shows;
    }

}
