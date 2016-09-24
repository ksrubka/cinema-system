package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class Calculation {

    private Set<TicketOrder> tickets;
    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);

    public Calculation() {
    }

    public Calculation(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public void calculatePrice(Set<TicketPrice> ticketPrices) {
        checkNotNull(ticketPrices);
        for (TicketOrder ticket : tickets) {
            for (TicketPrice ticketPrice : ticketPrices) {
                if (ticket.getKind().equals(ticketPrice.getType())) {
                    ticket.setUnitPrice(ticketPrice.getPrice());
                }
                ticket.setTotalPrice(ticketPrice.getPrice().multiply(new BigDecimal(ticket.getCount())));
                totalPrice = totalPrice.add(ticket.getUnitPrice().multiply(new BigDecimal(ticket.getCount())));
            }
        }
    }
}
