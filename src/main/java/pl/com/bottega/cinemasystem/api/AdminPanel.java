package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.List;

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
        Cinema cinema = new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate();
        Movie movie = new Movie(
                createMovieRequest.getTitle(),
                createMovieRequest.getDescription(),
                createMovieRequest.getMinAge(),
                createMovieRequest.getActors(),
                createMovieRequest.getGenres(),
                createMovieRequest.getLength()
        );
        movieRepository.save(movie);
    }

    @Transactional
    public void createShows(CreateShowsRequest createShowsRequest) {
        createShowsRequest.validate();
        List<Show> shows = ShowsFactory.create(createShowsRequest);
        shows.forEach(show -> showsRepository.save(show));
    }
}
