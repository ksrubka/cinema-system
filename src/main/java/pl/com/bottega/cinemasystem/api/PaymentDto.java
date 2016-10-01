package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.CreditCard;

public class PaymentDto {

    private String type;
    private Long cashierId;
    private CreditCard creditCard;

    public PaymentDto(String type, Long cashierId) {
        this.type = type;
        this.cashierId = cashierId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
