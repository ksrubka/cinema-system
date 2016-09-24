package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.Show;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MovieDtoWithShows {

    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
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
