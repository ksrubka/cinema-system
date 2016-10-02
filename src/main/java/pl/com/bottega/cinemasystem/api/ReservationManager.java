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
        //walidacja
        request.validateShowId();
        Show show = showsRepository.load(request.getShowId());
        validateShow(request, show);
        request.validate(show.getMovie().getMinAge());
        //mapowanie
        Set<Seat> seats = DtoMapper.getSeats(request.getSeats());
        Customer customer = DtoMapper.getCustomer(request.getCustomer());
        //walidacja
        checkIfSeatsCanBeReserved(seats, show);
        //zewnętrzny system
        Calculation calculation = callForCalculation(request, show);
        //logika dziedzinowa
        Reservation reservation = new Reservation(calculation.getTickets(), seats, customer, calculation.getTotalPrice(), show);
        reservationRepository.save(reservation);
        show.addReservation(reservation);

        return new CreateReservationResponse(reservation.getNumber());
    }

    private void validateShow(CreateReservationRequest request, Show show) {
        if (show == null) {
            throw new InvalidRequestException("No such show in repository, id: " + request.getShowId());
        }
    }

    private Calculation callForCalculation(CreateReservationRequest request, Show show) {
        Set<TicketOrderDto> ticketOrders = request.getTickets();
        CalculatePriceRequest priceRequest =
                new CalculatePriceRequest(show.getId(), ticketOrders);
        CalculatePriceResponse calculatePriceResponse = priceCalculator.calculatePrice(priceRequest);
        return calculatePriceResponse.getCalculation();
    }

    private void checkIfSeatsCanBeReserved(Set<Seat> seats, Show show) {
        Set<Reservation> reservations = show.getReservations();
        CinemaHall cinemaHall = new CinemaHall(reservations);
        cinemaHall.checkReservationPossibility(seats);
        if (!cinemaHall.isReservationPossible()) {
            throw new InvalidRequestException(cinemaHall.getErrorMessage());
        }
    }
}
