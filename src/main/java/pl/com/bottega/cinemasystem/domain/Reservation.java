package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Reservation {


    @EmbeddedId
    private ReservationNumber number;
    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketOrder> tickets;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Seat> bookedSeats;
    @OneToOne
    private Customer customer;
    private BigDecimal totalPrice;
    @ManyToOne
    private Show show;

    public Reservation() {
    }

    public Reservation(Set<TicketOrder> tickets, Set<Seat> bookedSeats,
                       Customer customer, BigDecimal totalPrice, Show show) {
        this.number = new ReservationNumber();
        this.tickets = tickets;
        this.bookedSeats = bookedSeats;
        this.customer = customer;
        this.status = ReservationStatus.PENDING;
        this.totalPrice = totalPrice;
        this.show = show;

    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrder> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Set<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationNumber getNumber() {
        return number;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setNumber(ReservationNumber number) {
        this.number = number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
