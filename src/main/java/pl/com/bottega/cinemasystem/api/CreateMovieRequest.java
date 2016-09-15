package pl.com.bottega.cinemasystem.api;

import java.util.List;

public class CreateMovieRequest {

    private MovieDto movie;


    public void validate() {
        checkState();
    }

    private void checkState() {
        checkTitle();
        checkDescription();
        checkMinAge();
        checkActors();
        checkGenres();
        checkLength();
    }

    private void checkTitle() {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty())
            throw new InvalidRequestException("movie title is required");
    }

    private void checkDescription() {
        if (movie.getDescription() == null || movie.getDescription().trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
    }

    private void checkMinAge() {
        if (movie.getMinAge() == null)
            throw new InvalidRequestException("minimal age is required");
    }

    private void checkActors() {
        if (movie.getActors() == null)
            throw new InvalidRequestException("actors list is required");
    }

    private void checkGenres() {
        if (movie.getGenres() == null)
            throw new InvalidRequestException("genres list is required");
    }

    private void checkLength() {
        if (movie.getLength() == null || movie.getLength() <= 0)
            throw new InvalidRequestException("movie length is required");
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
