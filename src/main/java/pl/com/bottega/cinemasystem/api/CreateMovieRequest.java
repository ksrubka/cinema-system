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

    public String getTitle() {
        return movie.getTitle();
    }

    public String getDescription() {
        return movie.getDescription();
    }

    public Integer getMinAge() {
        return movie.getMinAge();
    }

    public List<String> getActors() {
        return movie.getActors();
    }

    public List<String> getGenres() {
        return movie.getGenres();
    }

    public Integer getLength() {
        return movie.getLength();
    }
}
