package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ReservationNumber implements Serializable {

    private String number;

    public ReservationNumber() {
    }

    private ReservationNumber(String number) {
        this.number = number;
    }

    static ReservationNumber generateNumber() {
        return new ReservationNumber(UUID.randomUUID().toString());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
