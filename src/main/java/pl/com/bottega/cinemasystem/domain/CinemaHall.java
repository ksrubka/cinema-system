package pl.com.bottega.cinemasystem.domain;

import java.util.Set;

public class CinemaHall {

    private boolean[][] occupiedSeats;

    public CinemaHall(Set<Reservation> reservations) {
        this.occupiedSeats = generateOccupiedSeats(reservations);
    }

    private boolean[][] generateOccupiedSeats(Set<Reservation> reservations) {
        return null; //TODO
    }

    public boolean checkIfSeatsCanBeReserved(Set<Seat> seats) {
        return false; //TODO
    }

    public Set<Seat> getFreeSeats() {
        return null; //TODO
    }

    public Set<Seat> getOccupiedSeats() {
        return null; //TODO
    }

}
