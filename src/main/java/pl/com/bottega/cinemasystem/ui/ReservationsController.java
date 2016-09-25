package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.CreateReservationRequest;
import pl.com.bottega.cinemasystem.api.CreateReservationResponse;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    public CreateReservationResponse create(CreateReservationRequest reservationRequest){
        return null;
    }
}
