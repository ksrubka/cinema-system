package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TicketPrice {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private BigDecimal price;

    public TicketPrice() {
    }

    public TicketPrice(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public TicketPrice(Long id, String type, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}