package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private Integer row;
    private Integer number;

    public Seat() {
    }

    public Seat(Integer row, Integer number) {
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

    @Override
    public String toString() {
        return "seats{" +
                "row ='" + row + '\'' +
                ", number ='" + number + '\'' +
                '}';
    }
}
