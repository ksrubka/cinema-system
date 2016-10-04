package pl.com.bottega.cinemasystem.infrastructure;

import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Payment;
import pl.com.bottega.cinemasystem.domain.PaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAPaymentRepository implements PaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment load(Long id) {
        try {
            return entityManager.createQuery(
                    "FROM Payment p WHERE p.id = :id",
                    Payment.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No such payment in repository: id " + id);
        }
    }

    @Override
    public void save(Payment payment) {
        entityManager.persist(payment);
    }
}
