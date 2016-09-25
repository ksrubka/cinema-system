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
    private Integer count;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public TicketOrder() {
    }

    public TicketOrder(String kind, Integer count) {
        this.kind = kind;
        this.count = count;
    }

    public TicketOrder(String kind, Integer count, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.kind = kind;
        this.count = count;
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
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
