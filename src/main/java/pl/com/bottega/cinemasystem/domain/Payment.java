package pl.com.bottega.cinemasystem.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private PaymentType type;
    private Long cashierId;
    private boolean succesfull;
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL)
    private TransactionData transactionData;


    public Payment() {
    }

    public Payment(PaymentType type, Long cashierId) {
        this.type = type;
        this.cashierId = cashierId;
        this.date = LocalDateTime.now();
        this.succesfull = true;
    }

    public boolean isSuccesfull() {
        return succesfull;
    }
    public String getErrorMessage(){
       return this.transactionData.getErrorMessage();
    }
}
