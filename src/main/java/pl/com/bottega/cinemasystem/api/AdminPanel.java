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

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository,
                      MovieFactory movieFactory,
                      CinemaFactory cinemaFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
        this.movieFactory = movieFactory;
        this.cinemaFactory = cinemaFactory;
    }


    public AdminPanel(){

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
