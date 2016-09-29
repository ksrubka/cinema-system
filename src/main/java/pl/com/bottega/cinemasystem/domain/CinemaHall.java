package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.util.Set;
import java.util.TreeSet;

public class CinemaHall {

    private static final int ROWS = 10;
    private static final int SEATS = 15;
    private boolean[][] seats = new boolean[ROWS][SEATS];

    public CinemaHall(Set<Reservation> reservations) {
        generateOccupiedSeats(reservations);
    }

    private void generateOccupiedSeats(Set<Reservation> reservations) {
        Set<Seat> occupiedSeatsSet = new TreeSet<>();
        reservations.forEach(reservation -> occupiedSeatsSet.addAll(reservation.getBookedSeats()));
        occupiedSeatsSet.forEach(seat -> seats[seat.getRow()][seat.getNumber()] = true);
    }

    public void validateReservation(Set<Seat> seats) {
        checkIfHasEnoughSpareSeats(seats);
        checkIfSeatsAreAlreadyReserved(seats);
        if (chosenSeatsAreInTheSameRow(seats)) {
            if (!chosenSeatsAreNextToEachOther(seats)) {
                if (chosenSeatsCouldBeNextToEachOther(seats)) {
                    throw new InvalidRequestException("Reserved seats should be next to each other");
                }
            }
        } else {
            if (chosenSeatsCouldBeInTheSameRow()) {
                throw new InvalidRequestException("Reserved seats should be in the same row");
            }
        }
    }

    private boolean chosenSeatsAreInTheSameRow(Set<Seat> seats) {
        return false;
    }

    private boolean chosenSeatsAreNextToEachOther(Set<Seat> seats) {
        return false;
    }

    private boolean chosenSeatsCouldBeNextToEachOther(Set<Seat> seats) {
        return false;
    }

    private boolean chosenSeatsCouldBeInTheSameRow() {
        return false;
    }

    private void checkIfHasEnoughSpareSeats(Set<Seat> seats) {
        int spareSeatsCount = countSpareSeats();
        if (!hasEnoughSpareSeats(seats, spareSeatsCount)) {
            throw new InvalidRequestException("there is not enough spare seats to reserve");
        }
    }

    private int countSpareSeats() {
        int spareSeatsCount = 0;
        for (boolean[] row : this.seats) {
            for (boolean seat : row) {
                if (seatIsSpare(seat)) {
                    spareSeatsCount++;
                }
            }
        }
        return spareSeatsCount;
    }

    private boolean seatIsSpare(boolean seat) {
        return seat == false;
    }

    private boolean hasEnoughSpareSeats(Set<Seat> seats, int spareSeatsCount) {
        return spareSeatsCount >= seats.size();
    }

    private void checkIfSeatsAreAlreadyReserved(Set<Seat> seats) {
        seats.forEach(seat -> {
            if (seatIsReserved(seat)) {
                throw new InvalidRequestException("Seat row: " + seat.getRow() +
                        " number: " + seat.getNumber() + " is already reserved");
            }
        });
    }

    private boolean seatIsReserved(Seat seat) {
        return this.seats[seat.getRow() - 1][seat.getNumber() - 1];
    }

    public Set<Seat> getSpareSeats() {
        return null; //TODO
    }

    public Set<Seat> getSeats() {
        return null; //TODO
    }

}
