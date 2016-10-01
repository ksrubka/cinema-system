package pl.com.bottega.cinemasystem.domain;

public interface PaymentRepository {

    Payment load(Long id);
    void save(Payment payment);
}
