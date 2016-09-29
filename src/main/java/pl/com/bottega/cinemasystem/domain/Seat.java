package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seat implements Comparable {

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

    @Override
    public int compareTo(Object o2) {
        if (this == o2) {
            return 0;
        }
        Seat seat2 = (Seat) o2;
        if ((this.getRow() == seat2.getRow() && this.getNumber() < seat2.getNumber()) ||
                this.getRow() < seat2.getRow()) {
            return -1;
        }
        if ((this.getRow() == seat2.getRow() && this.getNumber() > seat2.getNumber()) ||
                this.getRow() > seat2.getRow()) {
            return 1;
        } else {
            return 0;
        }
    }
}
