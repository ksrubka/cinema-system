package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.MovieRepository;

import java.util.Collection;

public class CreateMovieRequest {

    private String title;
    private String description;
    private Collection<String> actors;

    private MovieRepository movieRepository;

    public CreateMovieRequest(String title, String description, Collection<String> actors) {
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    public void validate(MovieRepository movieRepository) {
        if (this.title == null || this.title.trim().isEmpty())
            throw new InvalidRequestException("Movie title is required");
        if (this.description == null || this.description.trim().isEmpty())
            throw new InvalidRequestException("movie description is required");

        if (movieRepository.load(this.title, this.description, this.actors) != null)
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

    public Collection<String> getActors() {
        return actors;
    }

    public void setActors(Collection<String> actors) {
        this.actors = actors;
    }
}
