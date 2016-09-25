package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.*;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationCatalog reservationCatalog;

    public CreateReservationResponse create(CreateReservationRequest reservationRequest) {
        return null;
    }

    public BrowseReservationResponse browseReservations(@ModelAttribute BrowseReservationRequest request) {
        return reservationCatalog.browseReservation(request);
    }
}
