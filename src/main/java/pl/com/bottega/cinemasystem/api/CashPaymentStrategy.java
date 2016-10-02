package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.Reservation;

@Component
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment pay(PaymentDto paymentDto, Reservation reservation) {
        return new Payment(paymentDto.getType(),paymentDto.getCashierId());
    }
}
