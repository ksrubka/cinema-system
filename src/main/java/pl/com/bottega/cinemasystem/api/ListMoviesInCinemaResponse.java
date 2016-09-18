package pl.com.bottega.cinemasystem.api;

import java.util.Collection;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public class ListMoviesInCinemaResponse {
    public Collection<MovieDtoWithShows> getMovies() {
        return movies;
    }

    Collection <MovieDtoWithShows> movies; //potrzebny osobną klasę na wzór movieDto z inną nazwą lub klasę wewnętrzną w tej klasie
}
