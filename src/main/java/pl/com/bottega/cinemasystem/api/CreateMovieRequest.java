package pl.com.bottega.cinemasystem.api;

import java.util.Collection;
import java.util.List;

public class CreateMovieRequest {

    private MovieDto movie;

    public void validate() {
        validateTitle();
        validateDescription();
        validateMinAge();
        validateActors();
        validateGenres();
        validateLength();
    }

    private void validateTitle() {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty())
            throw new InvalidRequestException("movie title is required");
    }

    private void validateDescription() {
        if (movie.getDescription() == null || movie.getDescription().trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
    }

    private void validateMinAge() {
        if (movie.getMinAge() == null)
            throw new InvalidRequestException("minimal age is required");
    }

    private void validateActors() {
        validateString(movie.getActors(), "actor list invalid");
    }

    private void validateGenres() {
        validateString(movie.getGenres(), "genres list invalid");
    }

    private void validateString(Collection<String> validations, String msg){
        if (validations == null || validations.isEmpty()) {
            throw new InvalidRequestException(msg);
        }
        validations.forEach(actor -> {
            if(actor.trim().isEmpty() || actor == null){
                throw new InvalidRequestException(msg);
            }
        });
    }

    private void validateLength() {
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
