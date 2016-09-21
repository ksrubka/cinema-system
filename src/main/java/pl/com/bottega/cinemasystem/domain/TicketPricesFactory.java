package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.UpdatePricesRequest;

import java.util.HashSet;
import java.util.Set;

public class TicketPricesFactory {

    public static Set<TicketPrice> createTickets(UpdatePricesRequest request) {
        Set<TicketPrice> tickets = new HashSet<>();
        request.getPrices().forEach((kind, price) -> {
            tickets.add(new TicketPrice(kind, price));
        });
        return tickets;
    }
}
