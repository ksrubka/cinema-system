package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Integer minAge;
    @ElementCollection
    private List<String> actors;
    @ElementCollection
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
