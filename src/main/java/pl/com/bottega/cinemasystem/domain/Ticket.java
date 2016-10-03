package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Seat seat;
    @ManyToOne
    private Show show;
    @ManyToOne
    private TicketPrice ticketPrice;

    public Ticket() {
    }

    public Ticket(Seat seat, Show show, TicketPrice ticketPrice) {
        this.seat = seat;
        this.show = show;
        this.ticketPrice = ticketPrice;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public TicketPrice getTicketPrice() {
        return ticketPrice;
    }
}
