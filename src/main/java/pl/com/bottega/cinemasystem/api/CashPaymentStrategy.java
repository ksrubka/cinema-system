package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.Payment;

@Component
public class CashPaymentStrategy implements PaymentStrategy {

    public CashPaymentStrategy() {
    }

    @Override
    public Payment pay(PaymentDto paymentDto) {
        return new Payment();
    }
}
