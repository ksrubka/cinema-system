package pl.com.bottega.cinemasystem.api.responses;

import pl.com.bottega.cinemasystem.api.dtos.ReservationDto;

import java.util.List;

public class BrowseReservationResponse {

    private List<ReservationDto> reservations;

    public BrowseReservationResponse(){}

    public BrowseReservationResponse(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
