package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TicketOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String kind;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public TicketOrder() {
    }

    public TicketOrder(String kind, Integer count) {
        this.kind = kind;
        this.quantity = count;
    }

    public TicketOrder(String kind, Integer count, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.kind = kind;
        this.quantity = count;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return quantity;
    }

    public void setCount(Integer count) {
        this.quantity = count;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
