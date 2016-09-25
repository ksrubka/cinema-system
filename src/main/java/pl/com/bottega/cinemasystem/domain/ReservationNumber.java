package pl.com.bottega.cinemasystem.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationNumber {

    @NaturalId
    private String number;

    public ReservationNumber(){}

    public ReservationNumber(String number){
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
