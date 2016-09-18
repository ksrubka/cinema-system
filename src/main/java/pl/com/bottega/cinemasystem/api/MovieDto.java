package pl.com.bottega.cinemasystem.api;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class MovieDto {

    private Long id;
    private String title;
    private String description;
    private Integer minAge;
    private List<String> actors;
    private List<String> genres;
    private Integer length;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
