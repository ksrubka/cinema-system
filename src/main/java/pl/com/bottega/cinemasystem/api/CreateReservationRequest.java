package pl.com.bottega.cinemasystem.api;

public class CreateReservationRequest {
    Long showId;
    TicketOrderDto tickets;
    Set <SeatDto> seats;
    CustomerDto customer;

    public void validate(){

    }
}
