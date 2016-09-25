package pl.com.bottega.cinemasystem.domain;

import java.util.Set;

public class CinemaHall {

    private char[][] tablic;

    public CinemaHall(char[][] tablic) {
        this.tablic = tablic;
    }

    public boolean checkIfSeatsCanBeReserved(Set<Seat> seats) {
        return false; //TODO
    }

    public CinemaHall(Set<Reservation> reservations) {

    }

    public Set<Seat> getFreeSeats() {
        return null; //TODO
    }

    public Set<Seat> getOccupiedSeats() {
        return null; //TODO
    }


}
