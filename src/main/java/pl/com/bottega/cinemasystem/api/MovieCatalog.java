package pl.com.bottega.cinemasystem.api;

import java.time.LocalDate;

public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date);
}
