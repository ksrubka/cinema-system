package pl.com.bottega.cinemasystem.api;

import java.util.Collection;

public class ListMoviesInCinemaResponse {
    public Collection<MovieDtoWithShows> getMovies() {
        return movies;
    }

    Collection <MovieDtoWithShows> movies; //potrzebny osobną klasę na wzór movieDto z inną nazwą lub klasę wewnętrzną w tej klasie
}
