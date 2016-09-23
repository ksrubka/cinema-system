package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.CreateMovieRequest;

public class MovieFactory {

    public static Movie createMovie(CreateMovieRequest request) {
        return new Movie(request.getTitle(), request.getDescription(), request.getMinAge(), request.getActors(),
                request.getGenres(), request.getLength());
    }
}
