package pl.com.bottega.cinemasystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionData {

    @Id
    @GeneratedValue
    private Long id;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
}
