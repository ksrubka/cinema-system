package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Customer;
import pl.com.bottega.cinemasystem.domain.Seat;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Set;

public class DtoMapper {

    public static Set<TicketOrder> getTicketOrders(Set<TicketOrderDto> tickets) {
        return null;
//        return tickets.stream().map(ticket -> new TicketOrder(ticket.getKind(), ticket.getCount()));
    }

    public static Set<Seat> getSeats(Set<SeatDto> seats) {
        return null;
    }

    public static Customer getCustomer(CustomerDto customer) {
        return null;
    }
}
