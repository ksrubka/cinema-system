package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.MovieRepository;

import java.util.Properties;

@Service
public class AdminPanel {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    @Transactional
    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        createCinemaRequest.validate(cinemaRepository);
        Cinema cinema = new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void createMovie(CreateMovieRequest createMovieRequest) {

    }

    /*
    @Transactional
    public void createShow(CreateShowRequest createShowRequest) {

    }*/
}
