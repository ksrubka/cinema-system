package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ReservationNumber implements Serializable {

    private String number;

    public ReservationNumber() {
        this.number = UUID.randomUUID().toString();
    }

}
