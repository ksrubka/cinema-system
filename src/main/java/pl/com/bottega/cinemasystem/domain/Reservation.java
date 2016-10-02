package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.InvalidRequestException;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private BigDecimal totalPrice;

    @ManyToOne
    private Show show;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Payment> payments;

    public Reservation() {
    }

    public Reservation(Set<TicketOrder> tickets, Set<Seat> bookedSeats,
                       Customer customer, BigDecimal totalPrice, Show show) {
        this.number = ReservationNumber.generate();
        this.tickets = tickets;
        this.bookedSeats = bookedSeats;
        this.customer = customer;
        this.status = ReservationStatus.PENDING;
        this.totalPrice = totalPrice;
        this.show = show;
    }

    public void addPayment(Payment payment) {
        if (payment.isSuccesfull()) {
            this.setStatus(ReservationStatus.PAID);
        }
        else {
            this.status = ReservationStatus.PAYMENT_FAILED;
        }
        payments.add(payment);
    }

    public boolean canBePaid() {
        return (this.getStatus().equals(ReservationStatus.PENDING) ||
                this.getStatus().equals(ReservationStatus.PAYMENT_FAILED));
    }

    public boolean isPaid() {
        return status == ReservationStatus.PAID;
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
