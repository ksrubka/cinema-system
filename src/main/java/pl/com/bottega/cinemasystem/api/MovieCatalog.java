package pl.com.bottega.cinemasystem.api;

import java.time.LocalTime;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalTime date);
}
