package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Movie;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */
public class ListMoviesInCinemaResponse {
    public ListMoviesInCinemaResponse(Collection<Movie> moviesCollection) {
        this.movies = new HashSet<>();
        for (Movie movie: moviesCollection){
            this.movies.add(new MovieDtoWithShows(movie));
        }
    }

    public Collection<MovieDtoWithShows> getMovies() {
        return movies;
    }

    Collection <MovieDtoWithShows> movies;

}
