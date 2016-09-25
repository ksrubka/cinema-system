package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.CreateReservationRequest;
import pl.com.bottega.cinemasystem.api.CreateReservationResponse;
import pl.com.bottega.cinemasystem.api.ReservationManager;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;

    public ReservationsController(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public CreateReservationResponse create(CreateReservationRequest reservationRequest){
        return reservationManager.createReservation(reservationRequest);
    }
}
