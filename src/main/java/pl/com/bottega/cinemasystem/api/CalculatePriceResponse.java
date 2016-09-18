package pl.com.bottega.cinemasystem.api;


import pl.com.bottega.cinemasystem.domain.Calculation;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

public class CalculatePriceResponse {

    private Calculation calculation;
    private Collection<TicketOrder> tickets;

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public Collection<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketOrder> tickets) {
        this.tickets = tickets;
    }
}
