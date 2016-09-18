package pl.com.bottega.cinemasystem.api;

import java.time.LocalTime;

public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalTime date);
}
