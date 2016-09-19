package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.Collection;

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
        cinemaRepository.validateIfExists(createCinemaRequest.getName(), createCinemaRequest.getCity());
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
    public void createShows(CreateShowsRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getCinemaId());
        Movie movie = movieRepository.load(request.getMovieId());
        Collection<Show> shows = showsFactory.getShows(cinema, movie, request);
        saveShows(shows);
    }

    private void saveShows(Collection<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }

    @Transactional
    public void updatePrices(UpdatePricesRequest request) {

    }

}
