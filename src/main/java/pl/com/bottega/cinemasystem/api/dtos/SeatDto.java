package pl.com.bottega.cinemasystem.api.dtos;

public class SeatDto {

    private Integer row;
    private Integer number;

    public SeatDto() {
    }

    public SeatDto(Integer row, Integer number) {
        this.row = row;
        this.number = number;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
