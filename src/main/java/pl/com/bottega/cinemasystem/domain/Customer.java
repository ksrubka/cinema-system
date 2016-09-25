package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;

@Entity
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String phonenumber;
}
