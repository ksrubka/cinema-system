package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;
import java.util.Set;


public class Calculation {
    private BigDecimal total;
    private Set<TicketOrder> ticketOrder;

    public Calculation(BigDecimal total, Set<TicketOrder> ticketOrder) {
        this.total = total;
        this.ticketOrder = ticketOrder;
    }

    public Calculation() {

    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public Set<TicketOrder> getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(Set<TicketOrder> ticketOrder) {
        this.ticketOrder = ticketOrder;
    }
}
