package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class Calculation {

    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
    private Set<TicketOrder> ticketOrder;

    public Calculation() {
    }

    public Calculation(Set<TicketOrder> ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

    public BigDecimal getTotal() {
        return totalPrice;
    }

    public void setTotal(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<TicketOrder> getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(Set<TicketOrder> ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

    public void calculatePrice(Set<TicketPrice> ticketPrices) {
        checkNotNull(ticketPrices);
        for (TicketOrder ticket : ticketOrder) {
            for (TicketPrice price : ticketPrices) {
                if (ticket.getKind().equals(price.getType())) {
                    BigDecimal unitPrice = ticket.setUnitPrice(price.getPrice());
                    unitPrice.multiply(new BigDecimal(ticket.getCount()));
                }
            }
        }
    }
}
