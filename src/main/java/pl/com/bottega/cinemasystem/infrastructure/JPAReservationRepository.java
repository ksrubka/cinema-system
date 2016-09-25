package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.domain.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JPAReservationRepository implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

}
