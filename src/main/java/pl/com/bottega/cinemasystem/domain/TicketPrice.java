package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class TicketPrice {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private BigDecimal price;

    public TicketPrice(String type, BigDecimal price) {
        this.type = type;
        this.price = price;

    }

    public TicketPrice() {
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
