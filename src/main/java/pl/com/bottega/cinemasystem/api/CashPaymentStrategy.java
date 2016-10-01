package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Payment;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment pay(PaymentDto paymentDto) {
        return new Payment();
    }
}
