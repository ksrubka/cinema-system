package pl.com.bottega.cinemasystem.api;

import java.util.Set;

public class CreateReservationRequest {
    Long showId;
    TicketOrderDto tickets;
    Set<SeatDto> seats;
    CustomerDto customer;

    public void validate(){

    }
}
