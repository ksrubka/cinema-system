package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;
import java.util.Set;


public class Calculation {
    private BigDecimal totalPrice;
    private Set<TicketOrder> ticketOrder;

    public Calculation(BigDecimal totalPrice, Set<TicketOrder> ticketOrder) {
        this.totalPrice = totalPrice;
        this.ticketOrder = ticketOrder;
    }

    public Calculation() {

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
}
