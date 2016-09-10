package pl.com.bottega.cinemasystem.api;

import java.util.List;

public class CreateMovieRequest {

    private MovieDto movie;

    public void validate() {
        movie.validate();
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    String getTitle() {
        return movie.getTitle();
    }

    String getDescription() {
        return movie.getDescription();
    }

    Integer getMinAge() {
        return movie.getMinAge();
    }

    List<String> getActors() {
        return movie.getActors();
    }

    List<String> getGenres() {
        return movie.getGenres();
    }

    Integer getLength() {
        return movie.getLength();
    }
}
