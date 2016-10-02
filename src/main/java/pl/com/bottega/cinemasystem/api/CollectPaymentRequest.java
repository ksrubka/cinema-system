package pl.com.bottega.cinemasystem.api;

import com.stripe.Stripe;

public class CollectPaymentRequest {

    private PaymentDto payment;
    private String reservationNumber;

    public CollectPaymentRequest() {
    }

    public CollectPaymentRequest(PaymentDto payment) {
        this.payment = payment;
    }

    public void validate() {
        validatePaymentType();
        validateCashierId();
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
        if ((!payment.getType().trim().toLowerCase()
                .equals("cash")) ||
                (!payment.getType().trim().toLowerCase()
                        .equals("credit card"))) {
            throw new InvalidRequestException("Incorrect payment type: " + payment.getType());
        }
        if (payment.getType().trim().toLowerCase().equals("cash")) {
            if (payment.getCreditCard() != null) {
                throw new InvalidRequestException("Credit card should not be declared in cash payment");
            }
        }
        if (payment.getType().trim().toLowerCase().equals("credit card")) {
            if (payment.getCreditCard() == null) {
                throw new InvalidRequestException("Credit card should be declared in credit card payment");
            }
        }
    }

    private void validateCashierId() {
        if (payment.getCashierId() == null) {
            throw new InvalidRequestException("Cshier id can not be null");
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
