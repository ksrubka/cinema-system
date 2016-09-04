package pl.com.bottega.cinemasystem.domain;

import java.util.Date;

public class Show {

    private Cinema cinema;
    private Movie movie;
    private Date date;


    public Show(Cinema cinema, Movie movie, Date date) {
        this.cinema = cinema;
        this.movie = movie;
        this.date = date;
    }


}
