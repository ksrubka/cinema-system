package pl.com.bottega.cinemasystem.api;


import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Collection;
import java.util.Set;


public class CalculatePriceRequest {

    private Long showId;
    private Set<TicketOrder> tickets;

    public CalculatePriceRequest(){}

    public CalculatePriceRequest(Long showId, Set<TicketOrder> tickets){
        this.showId = showId;
        this.tickets = tickets;
    }

    public void validate() {
        validateShow(showId);
        validateTickets(tickets);
    }

    private void validateTickets(Set<TicketOrder> ticketOrders) {
        if (ticketOrders == null || ticketOrders.isEmpty()) {
            throw new InvalidRequestException("ticket list can not be empty");
        }
    }

    private void validateShow(Long showId) {
        if (showId== null || showId == 0) {
            throw new InvalidRequestException("show id can not be null or zero");
        }
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
}
