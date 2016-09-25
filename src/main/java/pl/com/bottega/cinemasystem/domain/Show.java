package pl.com.bottega.cinemasystem.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDate date, LocalTime time) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
        this.time = time;
    }

    public void calculatePrices(Calculation calculation) {
        calculation.calculatePrice(movie.getTicketPrices());
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}