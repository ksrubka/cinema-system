package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.utils.UUIDValidator;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.Reservation;
import pl.com.bottega.cinemasystem.domain.ReservationRepository;

@Service
public class CashierPanel {

    private ReservationRepository reservationRepository;
    private CashPaymentStrategy cashPayment;
    private CreditCardPaymentStrategy creditCardPayment;
    private PdfFacade pdfFacade;

    public CashierPanel(ReservationRepository reservationRepository, CashPaymentStrategy cashPayment,
                        CreditCardPaymentStrategy creditCardPayment, PdfFacade pdfFacade) {
        this.reservationRepository = reservationRepository;
        this.cashPayment = cashPayment;
        this.creditCardPayment = creditCardPayment;
        this.pdfFacade = pdfFacade;
    }

    @Transactional
    public CollectPaymentResponse collectPayment(CollectPaymentRequest request) {
        request.validate();
        Reservation reservation = reservationRepository.load(request.getReservationNumber());
        validateReservation(reservation);
        Payment payment =  processPayment(request, reservation);
        reservation.addPayment(payment);
        return new CollectPaymentResponse(payment);
    }

    private Payment processPayment(CollectPaymentRequest request, Reservation reservation) {

        if(request.getPayment().getType().equals("cash")){
            return cashPayment.pay(request.getPayment(), reservation);
        }
        else return creditCardPayment.pay(request.getPayment(), reservation);
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

    @Transactional
    public String getPdfTickets(String reservationNumber) {
        validateReservationNumber(reservationNumber);
        Reservation reservation = reservationRepository.load(reservationNumber);
        checkIfReservationExists(reservation);
        return pdfFacade.createPdf(reservation);
    }

    private void validateReservationNumber(String reservationNumber) {
        UUIDValidator uuidValidator = new UUIDValidator();
        uuidValidator.validate(reservationNumber);
    }
}
