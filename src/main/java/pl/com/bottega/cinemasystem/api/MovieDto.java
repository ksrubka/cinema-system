package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.MovieRepository;

import java.util.List;

public class MovieDto {

    private String title;
    private String description;
    private Integer minAge;
    private List<String> actors;
    private List<String> genres;
    private Integer length;

    public void validate(MovieRepository movieRepository) {
        checkState();
        checkIfExists(movieRepository);
    }

    private void checkIfExists(MovieRepository movieRepository) {
        if (movieRepository.load(this.title, this.description, this.minAge, this.actors, this.genres, this.length) != null)
            throw new InvalidRequestException("This movie is already in your system");
    }

    private void checkState() {
        checkTitle();
        checkDescription();
        checkMinAge();
        checkActors();
        checkGenres();
        checkLength();
    }

    private void checkLength() {
        if (this.length == null || this.length <= 0)
            throw new InvalidRequestException("movie length is required");
    }

    private void checkGenres() {
        if (this.genres == null)
            throw new InvalidRequestException("genres list is required");
    }

    private void checkActors() {
        if (this.actors == null)
            throw new InvalidRequestException("actors list is required");
    }

    private void checkMinAge() {
        if (this.minAge == null)
            throw new InvalidRequestException("minimal age is required");
    }

    private void checkDescription() {
        if (this.description == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
    }

    private void checkTitle() {
        if (this.title == null || this.title.trim().isEmpty())
            throw new InvalidRequestException("movie title is required");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
