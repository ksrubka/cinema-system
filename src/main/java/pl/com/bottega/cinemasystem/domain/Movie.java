package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer minAge;
    private Integer length;

    @ElementCollection
    private Set<String> actors;

    @ElementCollection
    private Set<String> genres;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketPrice> ticketPrices;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @OrderBy("time")
    private Set<Show> shows;

    public Movie() {
    }

    public Movie(String title, String description, Integer minAge,
                 Set<String> actors, Set<String> genres, Integer length) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
    }


    public void addShows(Collection<Show> shows) {
        this.shows.addAll(shows);
    }

    public void updatePrices(Set<TicketPrice> ticketPricesSet) {
        this.ticketPrices.clear();
        this.ticketPrices.addAll(ticketPricesSet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Set<Show> getShows() {
        return shows;
    }

    public Set<TicketPrice> getTicketPrices() {
        return ticketPrices;
    }
}