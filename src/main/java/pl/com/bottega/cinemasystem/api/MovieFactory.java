package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Movie;

public class MovieFactory {

    public static Movie createMovie(CreateMovieRequest request) {
        return new Movie (request.getTitle(), request.getDescription(), request.getMinAge(), request.getActors(),
                request.getGenres(), request.getLength());
    }
}
