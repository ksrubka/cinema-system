package pl.com.bottega.cinemasystem.infrastructure;

import pl.com.bottega.cinemasystem.api.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.MovieCatalog;

import java.time.LocalTime;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public class JPAMovieCatalog implements MovieCatalog {
    @Override
    public ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalTime date) {
        return null;
    }
}
