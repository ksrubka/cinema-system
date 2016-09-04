package pl.com.bottega.cinemasystem.domain;

import java.util.List;


public class Movie {

    private String title;
    private String description;
    private Integer minAge;
    private List<String> actors;
    private List<String> genres;
    private Integer length;

    public Movie(String title, String description, Integer minAge, List<String> actors, List<String> genres, Integer length) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
    }
}
