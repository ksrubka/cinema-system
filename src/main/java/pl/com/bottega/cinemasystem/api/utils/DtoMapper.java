package pl.com.bottega.cinemasystem.api.utils;

import pl.com.bottega.cinemasystem.api.*;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DtoMapper {


    public static Set<TicketOrder> getTicketOrders(Set<TicketOrderDto> tickets) {
        return tickets.stream()
                .map(ticket -> new TicketOrder(ticket.getKind(), ticket.getCount()))
                .collect(Collectors.toSet());
    }

    public static Set<Seat> getSeats(Set<SeatDto> seats) {
        return seats.stream()
                .map(seat -> new Seat(seat.getRow(), seat.getNumber()))
                .collect(Collectors.toSet());
    }

    public static Customer getCustomer(CustomerDto customer) {
        return new Customer(customer.getFirstName(), customer.getLastName(),
                customer.getEmail(), customer.getPhone());
    }

    public static List<ReservationDto> getReservationDto(List<Reservation> resultList) {
        return resultList.stream().map(reservation -> new ReservationDto(reservation.getNumber().getNumber(),
                getShowDto(reservation.getShow()), getMovieDto(reservation.getShow().getMovie()),
               getTicketOrder(reservation.getTickets()), getSeatDto(reservation.getBookedSeats()),
                getCustomerDto(reservation.getCustomer()),reservation.getStatus())).collect(Collectors.toList());
    }

    public static ShowDto getShowDto(Show show) {
        return new ShowDto(show.getId(), show.getTime());
    }

    public static MovieDto getMovieDto(Movie movie) {
        return new MovieDto(movie.getTitle(), movie.getDescription(),
                movie.getMinAge(), movie.getActors(), movie.getGenres(), movie.getLength());
    }
    public static Set<TicketOrderDto> getTicketOrder(Set<TicketOrder> ticketOrders){
        return ticketOrders.stream().map(ticketOrder -> new TicketOrderDto(
                ticketOrder.getKind(), ticketOrder.getCount())).collect(Collectors.toSet());
    }
    public static Set<SeatDto> getSeatDto(Set<Seat> seats){
        return seats.stream().map(seat -> new SeatDto(seat.getRow(), seat.getNumber())).collect(Collectors.toSet());
    }
    public static CustomerDto getCustomerDto(Customer customer){
        return new CustomerDto(customer.getFirstName(),customer.getLastName(), customer.getEmail(), customer.getPhoneNumber());
    }
}
