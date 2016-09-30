package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Set;

public class CalculatePriceRequest {

    private Long showId;
    private Set<TicketOrderDto> tickets;

    public CalculatePriceRequest() {
    }

    public CalculatePriceRequest(Long showId, Set<TicketOrderDto> tickets) {
        this.showId = showId;
        this.tickets = tickets;
    }

    public void validate() {
        validateShowId();
        validateTickets();
    }

    private void validateTickets() {
        if (tickets == null || tickets.isEmpty()) {
            throw new InvalidRequestException("ticket list can not be empty");
        }
        tickets.forEach(ticketOrder -> {
            ValidationUtils.validateString(ticketOrder.getKind(), "Incorrect");
        });
    }

    private void validateShowId() {
        ValidationUtils.validateId(showId, "show id can not be null or zero");
    }

    public Set<TicketOrderDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrderDto> tickets) {
        this.tickets = tickets;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
