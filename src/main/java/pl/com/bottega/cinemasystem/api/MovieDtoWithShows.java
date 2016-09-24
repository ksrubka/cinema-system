package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.Show;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieDtoWithShows {

    private String title;
    private String description;
    private Set<String> actors;
    private Set<String> genres;
    private int minAge;
    private int length;
    private Collection<ShowDto> shows;

    public MovieDtoWithShows(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.minAge = movie.getMinAge();
        this.length = movie.getLength();
        this.shows = new HashSet<>();
        for (Show show : movie.getShows()) {
            shows.add(new ShowDto(show));
        }
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

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Collection<ShowDto> getShows() {
        return shows;
    }

    public void setShows(Collection<ShowDto> shows) {
        this.shows = shows;
    }
}
