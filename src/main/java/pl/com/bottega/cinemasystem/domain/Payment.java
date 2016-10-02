package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Long cashierId;
    private boolean succesfull;
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL)
    private TransactionData transactionData;

    public Payment() {
    }

    public Payment(String type, Long cashierId) {
        this.type = type;
        this.cashierId = cashierId;
        this.date = LocalDateTime.now();
    }
}
