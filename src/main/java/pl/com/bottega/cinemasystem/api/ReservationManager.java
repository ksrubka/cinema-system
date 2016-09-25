package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemasystem.domain.*;

@Service
public class ReservationManager {

    private ShowsRepository showsRepository;
    private ReservationRepository reservationRepository;

    public ReservationManager(ShowsRepository showsRepository, ReservationRepository reservationRepository) {
        this.showsRepository = showsRepository;
        this.reservationRepository = reservationRepository;
    }

    public CreateReservationResponse createReservation(CreateReservationRequest reservationRequest){
        //ticketOrders bookedSeats customer reservationNumber status
        reservationRequest.validateShowId();
        Show show = showsRepository.load(reservationRequest.getShowId());
        Movie movie = show.getMovie();
        reservationRequest.validate(movie.getMinAge());

        Reservation reservation = new Reservation();
        return new CreateReservationResponse("reservationNr");
    }
}
