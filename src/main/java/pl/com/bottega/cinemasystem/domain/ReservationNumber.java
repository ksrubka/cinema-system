package pl.com.bottega.cinemasystem.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ReservationNumber implements Serializable {

    @NaturalId
    private String number;

    public ReservationNumber() {
    }

    private ReservationNumber(String number) {
        this.number = number;
    }

    static ReservationNumber generate() {
        return new ReservationNumber(UUID.randomUUID().toString());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationNumber that = (ReservationNumber) o;

        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}