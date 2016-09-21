package pl.com.bottega.cinemasystem.api;


import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Collection;


public class CalculatePriceRequest {

    private Integer showId;
    private Collection<TicketOrder> tickets;

    public void validate() {
        showValidation(showId);
        ticketsValidation(tickets);
    }

    private void ticketsValidation(Collection<TicketOrder> ticketOrders) {
        if (ticketOrders.isEmpty()) {
            throw new InvalidRequestException("ticket list can not be empty");
        }
    }

    private void showValidation(Integer calculation) {
        if (calculation.equals(null) || calculation == 0) {
            throw new InvalidRequestException("show id can not be null or zero");
        }

    }

    public Collection<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

}
