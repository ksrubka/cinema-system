package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

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
        for (TicketOrder ticketOrder : tickets) {
            BigDecimal ticketPrice = getTicketPrice(ticketPrices, ticketOrder.getKind());
            ticketOrder.setUnitPrice(ticketPrice);
            ticketOrder.setTotalPrice(ticketOrder.getUnitPrice()
                    .multiply(new BigDecimal(ticketOrder.getCount())));
            totalPrice = totalPrice.add(ticketOrder.getTotalPrice());
        }
    }

    private BigDecimal getTicketPrice(Set<TicketPrice> ticketPrices, String ticketType) {
        for (TicketPrice ticketPrice : ticketPrices) {
            if (ticketType.equals(ticketPrice.getType())) {
                return ticketPrice.getPrice();
            }
        }
        throw new InvalidRequestException("Ticket type: " + ticketType + " was not specified for a given movie");
    }
}

