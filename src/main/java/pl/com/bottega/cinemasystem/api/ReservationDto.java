package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.ReservationStatus;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public class ReservationDto {

    private String number;
    private ShowDto show;
    private MovieDto movie;
    private Set<TicketOrderDto> tickets;
    private Set<SeatDto> seat;
    private CustomerDto customer;
    private ReservationStatus status;
    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);

    public ReservationDto(){}

    public ReservationDto(String number, ShowDto show, MovieDto movie, Set<TicketOrderDto> tickets,
                                     Set<SeatDto> seat, CustomerDto customer, ReservationStatus status,
                          BigDecimal totalPrice) {
        this.number = number;
        this.show = show;
        this.movie = movie;
        this.tickets = tickets;
        this.seat = seat;
        this.customer = customer;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ShowDto getShow() {
        return show;
    }

    public void setShow(ShowDto show) {
        this.show = show;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public Set<TicketOrderDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketOrderDto> tickets) {
        this.tickets = tickets;
    }

    public Set<SeatDto> getSeat() {
        return seat;
    }

    public void setSeat(Set<SeatDto> seat) {
        this.seat = seat;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}