package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemasystem.api.utils.DtoMapper;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationManager {

    private ShowsRepository showsRepository;
    private ReservationRepository reservationRepository;
    private PriceCalculator priceCalculator;

    public ReservationManager(ShowsRepository showsRepository, ReservationRepository reservationRepository, PriceCalculator priceCalculator) {
        this.showsRepository = showsRepository;
        this.reservationRepository = reservationRepository;
        this.priceCalculator = priceCalculator;
    }

    public CreateReservationResponse createReservation(CreateReservationRequest reservationRequest){
        //ticketOrders bookedSeats customer reservationNumber status
        reservationRequest.validateShowId();
        Show show = showsRepository.load(reservationRequest.getShowId());
        Movie movie = show.getMovie();
        reservationRequest.validate(movie.getMinAge());
        checkSeatsCanBeReserved(reservationRequest.getSeats(), show);
        Set<TicketOrder> ticketOrders = DtoMapper.getTicketOrders(reservationRequest.getTickets());
        Set<Seat> seats = DtoMapper.getSeats(reservationRequest.getSeats());
        Customer customer = DtoMapper.getCustomer(reservationRequest.getCustomer());
        CalculatePriceRequest priceRequest =
                new CalculatePriceRequest(reservationRequest.getShowId(), ticketOrders);
        CalculatePriceResponse calculatePriceResponse = priceCalculator.calculatePrice(priceRequest);
        Calculation calculation = calculatePriceResponse.getCalculation();
        Reservation reservation =
                new Reservation(calculation.getTickets(), seats, customer, calculation.getTotalPrice());
        show.addReservation(reservation);
        return new CreateReservationResponse(reservation.getNumber());
    }

    private void checkSeatsCanBeReserved(Set<SeatDto> seatDtos, Show show) {
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        Set<Seat> seats = mapSeatDtosToSeats(seatDtos);
         if (!cinemaHall.canReserve(seats)) {
             throw new InvalidRequestException("Seats can not be reserved");
         }
    }

    private Set<Seat> mapSeatDtosToSeats(Set<SeatDto> seatDtos) {
        Set<Seat> seats = new HashSet<>();
        seatDtos.forEach((seatDto -> {
            seats.add(new Seat(seatDto.getRow(), seatDto.getNumber()));
        }));
        return seats;
    }
}
