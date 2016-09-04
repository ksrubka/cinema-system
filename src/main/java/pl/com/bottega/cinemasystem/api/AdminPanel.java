package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;

public class AdminPanel {

    private CinemaRepository cinemaRepository;

    public void createCinema(CreateCinemaRequest createCinemaRequest) {
        createCinemaRequest.validate(cinemaRepository);
        Cinema cinema = new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
        cinemaRepository.save(cinema);
    }

    public void createMovie(CreateMovieRequest createMovieRequest) {

    }

    /*public void createShow(CreateShowRequest createShowRequest) {

    }*/
}
