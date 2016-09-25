package pl.com.bottega.cinemasystem.api;

public class TicketOrderDto {

    private String kind;
    private Integer count;

    public TicketOrderDto() {
    }

    public TicketOrderDto(String kind, Integer count) {
        this.kind = kind;
        this.count = count;
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
}
