package pl.com.bottega.cinemasystem.api;

import com.stripe.Stripe;
import pl.com.bottega.cinemasystem.domain.PaymentType;

public class CollectPaymentRequest {

    private PaymentDto payment;
    private String reservationNumber;

    public void validate() {
        validatePaymentType();
        if (payment.getCreditCard() == null) {
            validateCashierId();
        }
        validateReservationNumber();
        if (payment.getCreditCard() != null) {
            validateCreditCard();
        }
    }

    private void validateCreditCard() {
        if (payment.getCreditCard().getExpirationMonth() == null
                || payment.getCreditCard().getExpirationYear() == null
                || payment.getCreditCard().getCvc() == null
                || payment.getCreditCard().getNumber() == null)
            throw new InvalidRequestException("Fill all data");
    }

    private void validatePaymentType() {
        if (payment.getType() == null) {
            throw new InvalidRequestException("payment type can not be null");
        }
        if (payment.getType().equals(PaymentType.CASH)) {
            if (payment.getCreditCard() != null) {
                throw new InvalidRequestException("Credit card should not be declared in cash payment");
            }
        }
        if (payment.getType().equals(PaymentType.CREDIT_CARD)) {
            if (payment.getCreditCard() == null) {
                throw new InvalidRequestException("Credit card should be declared in credit card payment");
            }
        }
    }

    private void validateCashierId() {
        if (payment.getCashierId() == null) {
            throw new InvalidRequestException("Cashier id can not be null");
        }
    }

    private void validateReservationNumber() {
        if (reservationNumber == null) {
            throw new InvalidRequestException("reservation number can not be null");
        }
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }
}
