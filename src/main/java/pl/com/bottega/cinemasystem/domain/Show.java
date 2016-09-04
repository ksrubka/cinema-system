package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Cinema cinema;
    @OneToOne
    private Movie movie;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    public Show(Cinema cinema, Movie movie, Date date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }
}
