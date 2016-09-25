package pl.com.bottega.cinemasystem.api;

public class SeatDto {

    private Integer row;
    private Integer seat;

    public SeatDto() {
    }

    public SeatDto(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
