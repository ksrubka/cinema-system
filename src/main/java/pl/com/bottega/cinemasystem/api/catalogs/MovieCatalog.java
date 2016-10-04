package pl.com.bottega.cinemasystem.api.catalogs;

import pl.com.bottega.cinemasystem.api.responses.ListMoviesInCinemaResponse;

import java.time.LocalDate;

public interface MovieCatalog {
    ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date);
}
