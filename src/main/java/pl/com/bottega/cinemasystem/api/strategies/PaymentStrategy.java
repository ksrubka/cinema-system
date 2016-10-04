package pl.com.bottega.cinemasystem.api.strategies;

import pl.com.bottega.cinemasystem.api.dtos.PaymentDto;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.Reservation;

public interface PaymentStrategy {

    Payment pay(PaymentDto paymentDto, Reservation reservation);
}
