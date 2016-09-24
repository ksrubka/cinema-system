package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
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
    private List<String> actors;

    @ElementCollection
    private List<String> genres;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TicketPrice> ticketPrices;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Show> shows;

    public Movie() {
    }

    public Movie(String title, String description, Integer minAge,
                 List<String> actors, List<String> genres, Integer length) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
    }

    public Movie(String title, String description, Integer minAge,
                 List<String> actors, List<String> genres, Integer length,
                 Set<TicketPrice> ticketPrices, Set<Show> shows) {
        this.title = title;
        this.description = description;
        this.minAge = minAge;
        this.actors = actors;
        this.genres = genres;
        this.length = length;
        this.ticketPrices = ticketPrices;
        this.shows = shows;
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

    public Set<Show> getShows() {
        return shows;
    }

    public Set<TicketPrice> getTicketPrices() {
        return ticketPrices;
    }
}