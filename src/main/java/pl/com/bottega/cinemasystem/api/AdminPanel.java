package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;


import java.util.Collection;
import java.util.Set;

@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private ShowsRepository showsRepository;

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        createCinemaRequest.validate();
        Cinema cinema = CinemaFactory.createCinema(createCinemaRequest);
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate();
        Movie movie = MovieFactory.createMovie(createMovieRequest);
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(CreateShowsRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getCinemaId());
        Movie movie = movieRepository.load(request.getMovieId());
        Collection<Show> shows = ShowsFactory.createShows(cinema, movie, request);
        saveShows(shows);
        movie.addShows(shows);
        movieRepository.save(movie);
    }

    private void saveShows(Collection<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }

    @Transactional
    public void updatePrices(UpdatePricesRequest request) {
        request.validateMovieId();
        Movie movie = movieRepository.load(request.getMovieId());
        request.validate(movie);
        Set<TicketPrice> ticketPrices = TicketPricesFactory.createTickets(request);
        movie.updatePrices(ticketPrices);
    }
}