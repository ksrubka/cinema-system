package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cinema cinema;
    @ManyToOne
    private Movie movie;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, Date date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }
}
