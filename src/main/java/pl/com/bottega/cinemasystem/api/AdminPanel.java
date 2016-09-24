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
    private CinemaFactory cinemaFactory;
    private MovieFactory movieFactory;
    private ShowsFactory showsFactory;
    private TicketPricesFactory ticketPricesFactory;

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository,
                      CinemaFactory cinemaFactory,
                      MovieFactory movieFactory,
                      ShowsFactory showsFactory,
                      TicketPricesFactory ticketPricesFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
        this.cinemaFactory = cinemaFactory;
        this.movieFactory = movieFactory;
        this.showsFactory = showsFactory;
        this.ticketPricesFactory = ticketPricesFactory;
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
    public void createShows(CreateShowsRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getCinemaId());
        Movie movie = movieRepository.load(request.getMovieId());
        Collection<Show> shows = showsFactory.createShows(cinema, movie, request);
        saveShows(shows);
        movie.addShows(shows);
    }

    private void saveShows(Collection<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }

    @Transactional
    public void updatePrices(UpdatePricesRequest request) {
        request.validateMovieId();
        Movie movie = movieRepository.load(request.getMovieId());
        request.validate(movie.getMinAge());
        Set<TicketPrice> ticketPrices = ticketPricesFactory.createTickets(request);
        movie.updatePrices(ticketPrices);
    }
}