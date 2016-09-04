package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.MovieRepository;

public class CreateMovieRequest {

    private MovieDto movie;


    public void validate(MovieRepository movieRepository) {
        if(movie == null)
            throw new InvalidRequestException("Movie is required");
        movie.validate(movieRepository);
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }
}
