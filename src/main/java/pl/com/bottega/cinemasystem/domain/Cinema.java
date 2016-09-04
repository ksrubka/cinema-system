package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;

    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
