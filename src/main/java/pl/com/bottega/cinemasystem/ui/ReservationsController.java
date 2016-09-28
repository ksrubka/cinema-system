package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.*;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;
    private ReservationCatalog reservationCatalog;

    public ReservationsController(ReservationManager reservationManager, ReservationCatalog reservationCatalog) {
        this.reservationManager = reservationManager;
        this.reservationCatalog = reservationCatalog;
    }

    @PostMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest reservationRequest) {
        return reservationManager.createReservation(reservationRequest);
    }

    @GetMapping
    public BrowseReservationResponse browseReservations(@ModelAttribute BrowseReservationRequest request) {
        return reservationCatalog.browseReservation(request);
    }
}
