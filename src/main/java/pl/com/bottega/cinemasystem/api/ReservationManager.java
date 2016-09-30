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

    public ReservationManager(ShowsRepository showsRepository, ReservationRepository reservationRepository,
                              PriceCalculator priceCalculator) {
        this.showsRepository = showsRepository;
        this.reservationRepository = reservationRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        request.validateShowId();
        Show show = showsRepository.load(request.getShowId());
        request.validate(show.getMovie().getMinAge());
        Set<Seat> seats = DtoMapper.getSeats(request.getSeats());
        checkIfSeatsCanBeReserved(seats, show);
        Reservation reservation = create(request, seats, show);
        show.addReservation(reservation);
        reservationRepository.save(reservation);
        return new CreateReservationResponse(reservation.getNumber());
    }

    private Reservation create(CreateReservationRequest request, Set<Seat> seats, Show show) {
        Set<TicketOrderDto> ticketOrders = request.getTickets();
        Customer customer = DtoMapper.getCustomer(request.getCustomer());
        CalculatePriceRequest priceRequest =
                new CalculatePriceRequest(request.getShowId(), ticketOrders);
        CalculatePriceResponse calculatePriceResponse = priceCalculator.calculatePrice(priceRequest);
        Calculation calculation = calculatePriceResponse.getCalculation();
        return new Reservation(calculation.getTickets(), seats, customer, calculation.getTotalPrice(), show);
    }

    private void checkIfSeatsCanBeReserved(Set<Seat> seats, Show show) {
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        try {
            cinemaHall.canReserve(seats);
        } catch (Exception ex) {
            throw new InvalidRequestException(ex.getMessage());
        }
    }
}
