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
        Cinema cinema = cinemaRepository.load(createCinemaRequest.getCity(), createCinemaRequest.getName());
        if (cinema == null) {
            cinema = cinemaFactory.createCinema(createCinemaRequest);
        }
        else {
            throw new InvalidRequestException("Cinema already exists: "
                    + createCinemaRequest.getCity() + " " + createCinemaRequest.getName());
        }
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate();
        Movie movie = movieRepository.load(createMovieRequest.getTitle());
        if (movie == null) {
            movie = movieFactory.createMovie(createMovieRequest);
        }
        else {
            throw new InvalidRequestException("Movie already exists: " + createMovieRequest.getTitle());
        }
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(CreateShowsRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getCinemaId());
        Movie movie = movieRepository.load(request.getMovieId());
        checkIfCinemaExist(request.getCinemaId(), cinema);
        checkIfMovieExist(request.getMovieId(), movie);
        Collection<Show> shows = showsFactory.createShows(cinema, movie, request);
        saveShows(shows);
        movie.addShows(shows);
    }

    private void checkIfCinemaExist(Long cinemaId, Cinema cinema) {
        if (cinema == null) {
            throw new InvalidRequestException("Cinema does not exist: id " + cinemaId);
        }
    }

    private void checkIfMovieExist(Long movieId, Movie movie) {
        if (movie == null) {
            throw new InvalidRequestException("Movie does not exist: id " + movieId);
        }
    }

    private void saveShows(Collection<Show> shows) {
        shows.forEach(show -> showsRepository.save(show));
    }

    @Transactional
    public void updatePrices(UpdatePricesRequest request) {
        request.validateMovieId();
        Movie movie = movieRepository.load(request.getMovieId());
        checkIfMovieExist(request.getMovieId(), movie);
        request.validate(movie.getMinAge());
        Set<TicketPrice> ticketPrices = ticketPricesFactory.createTickets(request);
        movie.updatePrices(ticketPrices);
    }
}