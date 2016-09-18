package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.PriceCalculator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cinema cinema;
    @ManyToOne(cascade = CascadeType.ALL)
    private Movie movie;

    private LocalDateTime date;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}