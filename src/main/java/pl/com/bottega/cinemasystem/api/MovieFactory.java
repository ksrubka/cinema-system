package pl.com.bottega.cinemasystem.api;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.Movie;

@Component
public class MovieFactory {

    public Movie createMovie(CreateMovieRequest request) {
        return new Movie(request.getTitle(), request.getDescription(), request.getMinAge(), request.getActors(), request.getGenres(), request.getLength());
    }
}
