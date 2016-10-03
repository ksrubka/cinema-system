package pl.com.bottega.cinemasystem.infrastructure;

import pl.com.bottega.cinemasystem.domain.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketsFactory {

    public static Set<Ticket> create(Show show, Set<Seat> seats, Set<TicketOrder> ticketOrders) {
        List<TicketPrice> ticketKinds = prepareTicketPrices(ticketOrders);
        return prepareTickets(show, seats, ticketKinds);
    }

    private static List<TicketPrice> prepareTicketPrices(Set<TicketOrder> ticketOrders) {
        List<TicketPrice> ticketKinds = new ArrayList<>();
        for (TicketOrder ticketOrder : ticketOrders) {
            for (int i = 0; i < ticketOrder.getCount(); i++) {
                TicketPrice ticketPrice = new TicketPrice(ticketOrder.getKind(), ticketOrder.getUnitPrice());
                ticketKinds.add(ticketPrice);
            }
        }
        return ticketKinds;
    }

    private static Set<Ticket> prepareTickets(Show show, Set<Seat> seats, List<TicketPrice> ticketKinds) {
        Set<Ticket> tickets = new HashSet<>();
        int index = 0;
        for (Seat seat : seats) {
            tickets.add(new Ticket(seat, show, ticketKinds.get(index)));
            index++;
        }
        return tickets;
    }
}
