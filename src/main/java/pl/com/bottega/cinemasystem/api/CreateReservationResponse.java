package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.ReservationNumber;

public class CreateReservationResponse {

    private String reservationNumber;

    public CreateReservationResponse() {
    }

    public CreateReservationResponse(ReservationNumber reservationNumber) {
        this.reservationNumber = reservationNumber.getNumber();
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
