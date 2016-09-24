package pl.com.bottega.cinemasystem.domain;

import java.math.BigDecimal;

public class TicketOrder {

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

    public BigDecimal setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return totalPrice;
    }
}
