package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class Calculation {

    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
    private Set<TicketOrder> tickets;

    public Calculation() {
    }

    public Calculation(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public BigDecimal getTotal() {
        return totalPrice;
    }

    public void setTotal(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public BigDecimal calculatePrice(Set<TicketPrice> ticketPrices) {
        checkNotNull(ticketPrices);
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        BigDecimal unitPrice = new BigDecimal(BigInteger.ZERO);
        for (TicketOrder ticket : tickets) {
            for (TicketPrice ticketPrice : ticketPrices) {
                if (ticket.getKind().equals(ticketPrice.getType())) {
                    unitPrice = ticket.setUnitPrice(ticketPrice.getPrice());
                }
            }
            totalPrice = totalPrice.add(unitPrice.multiply(new BigDecimal(ticket.getCount())));
        }
        return totalPrice;
    }
}
