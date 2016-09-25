package pl.com.bottega.cinemasystem.domain;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Set;

public class Reservation {


    private Set<TicketOrder> tickets;
    private Set<Seat> bookedSeats;
    private Customer customer;
    private ReservationNumber number;
    private ReservationStatus status;

    public Reservation(){}

    public Reservation(Set<TicketOrder> tickets, Set<Seat> bookedSeats,
                       Customer customer, ReservationNumber number, ReservationStatus status) {
        this.tickets = tickets;
        this.bookedSeats = bookedSeats;
        this.customer = customer;
        this.number = number;
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

    public void setNumber(ReservationNumber number) {
        this.number = number;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

}
