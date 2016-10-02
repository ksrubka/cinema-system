package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.CreditCard;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.PaymentType;

public class PaymentDto {

    private PaymentType type;
    private Long cashierId;
    private CreditCard creditCard;

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }
}
