package pl.com.bottega.cinemasystem.api;


import pl.com.bottega.cinemasystem.domain.Calculation;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.util.Collection;
import java.util.Set;

public class CalculatePriceResponse {

    private Calculation calculation;
    private Set<TicketOrder> tickets;

    public CalculatePriceResponse() {
    }

    public CalculatePriceResponse(Calculation calculation, Set<TicketOrder> tickets) {
        this.calculation = calculation;
        this.tickets = tickets;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }
}
