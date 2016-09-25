package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.ReservationNumber;

public class CreateReservationResponse {

    private String reservationNumber;

    public CreateReservationResponse(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber.getNumber();
    }
}
