package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

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
        ValidationUtils.validateString(getTitle(), "movie title is required");
    }

    private void validateDescription() {
        ValidationUtils.validateString(getDescription(), "movie description is required");
    }

    private void validateMinAge() {
        if (getMinAge() == null || getMinAge() < 0) {
            throw new InvalidRequestException("minimal age is required");
        }
    }

    private void validateActors() {
        ValidationUtils.validateCollectionOfStrings(movie.getActors(), "actors list invalid");
    }

    private void validateGenres() {
        ValidationUtils.validateCollectionOfStrings(movie.getGenres(), "genres list invalid");
    }

    private void validateLength() {
        ValidationUtils.validateInteger(getLength(), "movie length is required");
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
