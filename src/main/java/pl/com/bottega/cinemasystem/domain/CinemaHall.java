package pl.com.bottega.cinemasystem.domain;

import java.util.HashSet;
import java.util.Set;

public class CinemaHall {

    private static final int ROWS = 10;
    private static final int SEATS = 15;
    private boolean[][] seats = new boolean[ROWS][SEATS];

    public CinemaHall(Set<Reservation> reservations) {
        generateOccupiedSeats(reservations);
    }

    private void generateOccupiedSeats(Set<Reservation> reservations) {
        Set<Seat> occupiedSeatsSet = new HashSet<>();
        reservations.forEach(reservation -> occupiedSeatsSet.addAll(reservation.getBookedSeats()));
        for (Seat seat : occupiedSeatsSet) {
             seats[seat.getRow()][seat.getNumber()] = true;
        }
    }

    public boolean canReserve(Set<Seat> seats) {
//        if ()
        return false; //TODO
    }

    public Set<Seat> getFreeSeats() {
        return null; //TODO
    }

    public Set<Seat> getSeats() {
        return null; //TODO
    }

}
