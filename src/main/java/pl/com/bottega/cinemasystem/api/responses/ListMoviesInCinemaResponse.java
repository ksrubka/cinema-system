package pl.com.bottega.cinemasystem.api.responses;

import pl.com.bottega.cinemasystem.api.dtos.MovieDtoWithShows;
import pl.com.bottega.cinemasystem.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;

public class ListMoviesInCinemaResponse {

    private Collection<MovieDtoWithShows> movies;

    public ListMoviesInCinemaResponse(Collection<Movie> moviesCollection) {
        this.movies = new ArrayList<>();
        for (Movie movie : moviesCollection) {
            this.movies.add(new MovieDtoWithShows(movie));
        }
    }

    public Collection<MovieDtoWithShows> getMovies() {
        return movies;
    }
}
