package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Customer;
import pl.com.bottega.cinemasystem.domain.Seat;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoMapper {

    public static Set<TicketOrder> getTicketOrders(Set<TicketOrderDto> tickets) {
      return tickets.stream().map(ticket -> new TicketOrder(ticket.getKind(), ticket.getCount())).collect(Collectors.toSet());
    }

    public static Set<Seat> getSeats(Set<SeatDto> seats) {
        return seats.stream().map(seat-> new Seat(seat.getRow(), seat.getNumber())).collect(Collectors.toSet());
    }

    public static Customer getCustomer(CustomerDto customer) {
        return new Customer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone());
    }
}
