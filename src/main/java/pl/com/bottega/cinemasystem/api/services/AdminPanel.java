package pl.com.bottega.cinemasystem.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemasystem.api.requests.CreateShowsRequest;
import pl.com.bottega.cinemasystem.api.requests.UpdatePricesRequest;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.Collection;
import java.util.Set;

@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;
    private ShowsRepository showsRepository;
    private ShowsFactory showsFactory;
    private TicketPricesFactory ticketPricesFactory;

    public AdminPanel(CinemaRepository cinemaRepository,
                      MovieRepository movieRepository,
                      ShowsRepository showsRepository,
                      ShowsFactory showsFactory,
                      TicketPricesFactory ticketPricesFactory) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.showsRepository = showsRepository;
        this.showsFactory = showsFactory;
        this.ticketPricesFactory = ticketPricesFactory;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest request) {
        request.validate();
        Cinema cinema = cinemaRepository.load(request.getCity(), request.getName());
        if (cinema == null) {
            cinema = new Cinema(request.getName(), request.getCity());
        } else {
            throw new InvalidRequestException("Cinema already exists: "
                    + request.getCity() + " " + request.getName());
        }
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest request) {
        request.validate();
        Movie movie = movieRepository.load(request.getTitle());
        if (movie == null) {
            movie = new Movie(request.getTitle(), request.getDescription(), request.getMinAge(), request.getActors(),
                    request.getGenres(), request.getLength());
        } else {
            throw new InvalidRequestException("Movie already exists: " + request.getTitle());
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
        Collection<Show> shows = showsFactory.createShows(cinema, movie, request.getShowDates());
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