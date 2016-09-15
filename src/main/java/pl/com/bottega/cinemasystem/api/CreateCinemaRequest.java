package pl.com.bottega.cinemasystem.api;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class CreateCinemaRequest {

    private CinemaDto cinema;

    public void validate() {
        cinema.validate();
        }

    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }



}
