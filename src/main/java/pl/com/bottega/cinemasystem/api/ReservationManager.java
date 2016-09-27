package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.utils.DtoMapper;
import pl.com.bottega.cinemasystem.domain.*;

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

    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest reservationRequest) {
        reservationRequest.validateShowId();
        Show show = showsRepository.load(reservationRequest.getShowId());
        Movie movie = show.getMovie();
        reservationRequest.validate(movie.getMinAge());
        Set<TicketOrder> ticketOrders = DtoMapper.getTicketOrders(reservationRequest.getTickets());
        Set<Seat> seats = DtoMapper.getSeats(reservationRequest.getSeats());
        Customer customer = DtoMapper.getCustomer(reservationRequest.getCustomer());
//        checkIfSeatsCanBeReserved(seats, show);
        CalculatePriceRequest priceRequest =
                new CalculatePriceRequest(reservationRequest.getShowId(), ticketOrders);
        CalculatePriceResponse calculatePriceResponse = priceCalculator.calculatePrice(priceRequest);
        Calculation calculation = calculatePriceResponse.getCalculation();
        Reservation reservation =
                new Reservation(calculation.getTickets(), seats, customer, calculation.getTotalPrice());
        show.addReservation(reservation);
        reservationRepository.save(reservation);
        return new CreateReservationResponse(reservation.getNumber());
    }

    private void checkIfSeatsCanBeReserved(Set<Seat> seats, Show show) {
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        if (!cinemaHall.canReserve(seats)) {
            throw new InvalidRequestException("Seats can not be reserved");
        }
    }
}
