package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Collection;

public class CalculatePriceRequest {

    private Long showId;
    private Collection<TicketOrder> tickets;

    public void validate() {
        validateShowId();
        validateTickets();
    }

    private void validateTickets() {
        if (tickets.isEmpty()) {
            throw new InvalidRequestException("ticket list can not be empty");
        }
        tickets.forEach(ticketOrder -> {
            ValidationUtils.validateString(ticketOrder.getKind(), "Incorrect");
        });
    }

    private void validateShowId() {
        ValidationUtils.validateId(showId, "show id can not be null or zero");
    }

    public Collection<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
