package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.MovieRepository;

@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    public AdminPanel(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        createCinemaRequest.validate(cinemaRepository);
        Cinema cinema = new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {
        createMovieRequest.validate(movieRepository);
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

    /*
    @Transactional
    public void createShow(CreateShowRequest createShowRequest) {

    }*/
}
