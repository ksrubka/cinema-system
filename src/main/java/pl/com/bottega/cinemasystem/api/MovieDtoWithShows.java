package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.Show;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MovieDtoWithShows {

    public String title;
    public String description;
    public List <String> actors;
    public List<String> genres;
    public int minAge;
    public int length;
    Collection<ShowDto> shows;


    public MovieDtoWithShows(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.genres = movie.getGenres();
        this.minAge = movie.getMinAge();
        this.length = movie.getLength();
        this.shows = new HashSet<>();
        for (Show show : movie.getShows()){
            shows.add(new ShowDto(show));
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Collection<ShowDto> getShows() {
        return shows;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActors() {
        return actors;
    }
}
