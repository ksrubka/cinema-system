package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TicketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String type;
    private BigDecimal price;

    public TicketPrice() {
    }

    public TicketPrice(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }
}