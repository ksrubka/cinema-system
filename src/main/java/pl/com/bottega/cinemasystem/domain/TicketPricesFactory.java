package pl.com.bottega.cinemasystem.domain;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.requests.UpdatePricesRequest;

import java.util.HashSet;
import java.util.Set;

@Component
public class TicketPricesFactory {

    public Set<TicketPrice> createTickets(UpdatePricesRequest request) {
        Set<TicketPrice> tickets = new HashSet<>();
        request.getPrices().forEach((kind, price) -> {
            tickets.add(new TicketPrice(kind, price));
        });
        return tickets;
    }
}