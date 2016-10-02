package pl.com.bottega.cinemasystem.api;

import com.stripe.exception.*;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.Reservation;

public interface PaymentStrategy {

    Payment pay(PaymentDto paymentDto, Reservation reservation);
}
