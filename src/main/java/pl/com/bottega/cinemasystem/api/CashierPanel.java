package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.*;

@Service
public class CashierPanel {

    private ReservationRepository reservationRepository;
    private CashPaymentStrategy cashPayment;
    private CreditCardPaymentStrategy creditCardPayment;


    public CashierPanel(ReservationRepository reservationRepository,
                        CashPaymentStrategy cashPayment, CreditCardPaymentStrategy creditCardPayment) {
        this.reservationRepository = reservationRepository;
        this.cashPayment = cashPayment;
        this.creditCardPayment = creditCardPayment;
    }

    @Transactional
    public CollectPaymentResponse collectPayment(CollectPaymentRequest request) {
        request.validate();
        Reservation reservation = reservationRepository.load(request.getReservationNumber());
        CollectPaymentResponse response = new CollectPaymentResponse();
        validateReservation(reservation);
        Payment payment = cashPayment.pay(request.getPayment());
        reservation.addPayment(payment);
        return response;
    }

    private void validateReservation(Reservation reservation) {
        checkIfReservationExists(reservation);
        if (!reservation.canBePaid()) {
            throw new InvalidRequestException("Reservation status is inappropriate for payment");
        }
    }

    private void checkIfReservationExists(Reservation reservation) {
        if (reservation == null) {
            throw new InvalidRequestException("Reservation does not exist");
        }
    }
}
