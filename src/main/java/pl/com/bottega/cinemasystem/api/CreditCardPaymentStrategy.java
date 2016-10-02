package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.Payment;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment pay(PaymentDto paymentDto) {
        return null;
    }
}
