package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment pay(PaymentDto paymentDto) {
        return null;
    }
}
