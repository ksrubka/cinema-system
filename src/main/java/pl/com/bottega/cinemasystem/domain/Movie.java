package pl.com.bottega.cinemasystem.domain;

import java.util.List;

public class Movie {

    private String title;
    private String description;
    private int minAge;
    private List<String> actors;
    private List<String> genres;
    private int length;

    public Movie(String title, String description, int minAge, List<String> actors, List<String> genres, int length) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
    }
}
