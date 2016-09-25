package pl.com.bottega.cinemasystem.domain;

import java.util.HashSet;
import java.util.Set;

public class CinemaHall {

    private boolean[][] seats = new boolean[10][15];

    public CinemaHall(Set<Reservation> reservations) {
        this.seats = generateOccupiedSeats(reservations);
    }

    private boolean[][] generateOccupiedSeats(Set<Reservation> reservations) {
        Set<Seat> occupiedSeatsSet = new HashSet<>();
        reservations.forEach(reservation -> occupiedSeatsSet.addAll(reservation.getBookedSeats()));
        for (Seat seat : occupiedSeatsSet) {
             seats[seat.getRow()][seat.getNumber()] = true;
        }
        return seats;
    }

    public boolean canReserve(Set<Seat> seats) {
        return false; //TODO
    }

    public Set<Seat> getFreeSeats() {
        return null; //TODO
    }

    public Set<Seat> getSeats() {
        return null; //TODO
    }

}
