package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.*;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private ReservationManager reservationManager;
    private ReservationCatalog reservationCatalog;
    private CashierPanel cashierPanel;

    public ReservationsController(ReservationManager reservationManager,
                                  ReservationCatalog reservationCatalog, CashierPanel cashierPanel) {
        this.reservationManager = reservationManager;
        this.reservationCatalog = reservationCatalog;
        this.cashierPanel = cashierPanel;
    }

    @PostMapping
    public CreateReservationResponse create(@RequestBody CreateReservationRequest reservationRequest) {
        return reservationManager.createReservation(reservationRequest);
    }

    @GetMapping
    public BrowseReservationResponse browseReservations(@ModelAttribute BrowseReservationRequest request) {
        return reservationCatalog.browseReservations(request);
    }

    @PutMapping("/{reservationNumber}/payments")
    public CollectPaymentResponse collectPayment(@PathVariable String reservationNumber,
                                                 @RequestBody CollectPaymentRequest request) {
        request.setReservationNumber(reservationNumber);
        return cashierPanel.collectPayment(request);
    }
}
