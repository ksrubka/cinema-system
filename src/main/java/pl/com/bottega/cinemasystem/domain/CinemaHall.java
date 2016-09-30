package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        occupiedSeatsSet.forEach(seat -> seats[seat.getRow() - 1][seat.getNumber() - 1] = true);
    }

    public void canReserve(Set<Seat> seats) {
        checkIfSeatsAreAlreadyReserved(seats);
        if (chosenSeatsAreInTheSameRow(seats)) {
            if (!chosenSeatsAreNextToEachOther(seats)) {
                if (chosenSeatsCouldBeNextToEachOther(seats)) {
                    throw new InvalidRequestException("Reserved seats should be next to each other");
                }
            }
        } else {
            if (chosenSeatsCouldBeInTheSameRow(seats)) {
                throw new InvalidRequestException("Reserved seats should be in the same row");
            }
        }
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

    private boolean chosenSeatsAreInTheSameRow(Set<Seat> seats) {
        int row = seats.iterator().next().getRow();
        for (Seat seat : seats) {
            if (seat.getRow() != row) {
                return false;
            }
        }
        return true;
    }

    private boolean chosenSeatsAreNextToEachOther(Set<Seat> seats) {
        List<Seat> seatsList = seats.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getNumber(), e2.getNumber()))
                .collect(Collectors.toList());
        for (int seatIndex = 0; seatIndex < seatsList.size() - 2; seatIndex++) {
            Seat currentSeat = seatsList.get(seatIndex);
            Seat nextSeat = seatsList.get(seatIndex + 1);
            if (currentSeat.getNumber() + 1 != nextSeat.getNumber()) {
                return false;
            }
        }
        return true;
    }

    private boolean chosenSeatsCouldBeNextToEachOther(Set<Seat> seats) {
        int row = seats.iterator().next().getRow();
        int spareSeatsNextToEachOther = countSpareSeatsNextToEachOther(row);
        return spareSeatsNextToEachOther >= seats.size();
    }

    private int countSpareSeatsNextToEachOther(int row) {
        boolean[] seatAvailabilities = seats[row + 1];
        int spareSeatsNextToEachOther = 1;
        for (int seatNr = 1; seatNr < seatAvailabilities.length; seatNr++) {
            if (seatAvailabilities[seatNr] == seatAvailabilities[seatNr - 1]) {
                spareSeatsNextToEachOther++;
            } else {
                spareSeatsNextToEachOther = 1;
            }
        }
        return spareSeatsNextToEachOther;
    }

    private boolean chosenSeatsCouldBeInTheSameRow(Set<Seat> seats) {
        Set<Integer> rows = seats.stream().map(seat -> seat.getRow()).collect(Collectors.toSet());
        for (int row : rows) {
            if (seats.size() <= countSpareSeatsInRow(row)) {
                return true;
            }
        }
        return false;
    }

    private int countSpareSeatsInRow(int row) {
        int spareSeatsCount = 0;
        for (boolean seatAvailability : seats[row + 1]) {
            if (!seatAvailability) {
                spareSeatsCount++;
            }
        }
        return spareSeatsCount;
    }
}
