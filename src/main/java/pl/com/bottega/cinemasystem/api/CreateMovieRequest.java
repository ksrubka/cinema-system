package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.MovieRepository;

import java.util.List;

public class CreateMovieRequest {

    private String title;
    private String description;
    private Integer minAge;
    private List<String> actors;
    private List<String> genres;
    private Integer length;

    public CreateMovieRequest(String title, String description, Integer minAge, List<String> actors, List<String> genres, Integer length) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
    }

    public void validate(MovieRepository movieRepository) {
        if (this.title == null || this.title.trim().isEmpty())
            throw new InvalidRequestException("Movie title is required");
        if (this.description == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
        if (this.minAge == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
        if (this.actors == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
        if (this.genres == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
        if (this.length == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required"); //BARDZO SMUTNY KODZIK :(


        if (movieRepository.load(this.title, this.description, this.minAge, this.actors, this.genres, this.length) != null)
            throw new InvalidRequestException("This movie is already in your system");

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

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
