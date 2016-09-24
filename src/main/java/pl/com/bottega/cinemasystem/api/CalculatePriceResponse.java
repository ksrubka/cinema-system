package pl.com.bottega.cinemasystem.api;


import pl.com.bottega.cinemasystem.domain.Calculation;
import pl.com.bottega.cinemasystem.domain.TicketOrder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public class CalculatePriceResponse {

    private Calculation calculation;
    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);

    public CalculatePriceResponse() {
    }

    public CalculatePriceResponse(Calculation calculation, Set<TicketOrder> tickets, BigDecimal totalPrice) {
        this.calculation = calculation;
        this.calculation.setTicketOrder(tickets);
        this.totalPrice = totalPrice;
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
