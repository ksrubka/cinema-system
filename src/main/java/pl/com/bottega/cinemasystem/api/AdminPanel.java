package pl.com.bottega.cinemasystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private ShowsRepository showsRepository;
    @Autowired
    private CinemaFactory cinemaFactory;

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository, CinemaFactory cinemaFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
        this.cinemaFactory = cinemaFactory;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        Cinema cinema = cinemaFactory.createCinema(createCinemaRequest);
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        Movie movie = MovieFactory.createMovie(createMovieRequest);
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(Long cinemaId, CreateShowsRequest createShowsRequest) {
        createShowsRequest.validate();
        List<Show> shows = getShows(cinemaId, createShowsRequest);
        saveShows(shows);
    }

    private List<Show> getShows(Long cinemaId, CreateShowsRequest createShowsRequest) {
        Cinema cinema = cinemaRepository.load(cinemaId);
        Movie movie = movieRepository.load(createShowsRequest.getMovieId());
        List<Date> dates = createShowsRequest.getShowDates();
        List<Show> shows = new ArrayList<>();
        dates.forEach(date -> shows.add(new Show(cinema, movie, date)));
        return shows;
    }

    private void saveShows(List<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }
}
