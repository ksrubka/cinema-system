package pl.com.bottega.cinemasystem.api;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.utils.DtoMapper;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.Reservation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {


    @Override
    public Payment pay(PaymentDto paymentDto, Reservation reservation) {
        Stripe.apiKey = "sk_test_XYeDQI5sP1vUee4QoRTPWaAB";

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", getTotalPriceInCents(reservation.getTotalPrice()));
        chargeParams.put("currency", "usd");
        chargeParams.put("source", source(paymentDto));
        chargeParams.put("description", "Charge for client");
        try {
            Charge.create(chargeParams);
        } catch (AuthenticationException e) {
            throw new InvalidRequestException("Authentication failed");
        } catch (com.stripe.exception.InvalidRequestException e) {
            throw new InvalidRequestException("Request invalid");
        } catch (APIConnectionException e) {
            throw new InvalidRequestException("API Connection failed");
        } catch (CardException e) {
            throw new InvalidRequestException("Card incorrect");
        } catch (APIException e) {
            throw new InvalidRequestException("API error");
        }
        return DtoMapper.getPayment(paymentDto);
    }

    private Map<String, Object> source(PaymentDto paymentDto) {
        Map<String, Object> data = new HashMap<>();
        data.put("exp_month", paymentDto.getCreditCard().getExpirationMonth());
        data.put("exp_year", paymentDto.getCreditCard().getExpirationYear());
        data.put("number", paymentDto.getCreditCard().getNumber());
        data.put("object", "card");
        data.put("cvc", paymentDto.getCreditCard().getCvc());
        return data;
    }
    private BigInteger getTotalPriceInCents(BigDecimal bigDecimal) {
        return bigDecimal.multiply(new BigDecimal(100)).toBigInteger();
    }

}