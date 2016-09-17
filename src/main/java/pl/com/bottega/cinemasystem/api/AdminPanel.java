package pl.com.bottega.cinemasystem.api;

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
    private MovieFactory movieFactory;
    private CinemaFactory cinemaFactory;
    private ShowsFactory showsFactory;

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository,
                      MovieFactory movieFactory,
                      CinemaFactory cinemaFactory,
                      ShowsFactory showsFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
        this.movieFactory = movieFactory;
        this.cinemaFactory = cinemaFactory;
        this.showsFactory = showsFactory;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        createCinemaRequest.validate();
        Cinema cinema = cinemaFactory.createCinema(createCinemaRequest);
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate();
        Movie movie = movieFactory.createMovie(createMovieRequest);
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(Long cinemaId, CreateShowsRequest createShowsRequest) {
        createShowsRequest.validate();
        List<Show> shows = showsFactory.getShows(cinemaId, createShowsRequest);
        saveShows(shows);
    }


    private void saveShows(List<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }
}
