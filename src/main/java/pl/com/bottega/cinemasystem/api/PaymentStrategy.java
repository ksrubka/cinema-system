package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Payment;

public interface PaymentStrategy {

    Payment pay(PaymentDto paymentDto);
}
