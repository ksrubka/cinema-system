package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Reservation;
import pl.com.bottega.cinemasystem.domain.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class JPAReservationRepository implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Reservation load(String number) {

       try {
           return entityManager.createQuery(
                   "From Reservation r where r.number.number = :number",
                   Reservation.class).setParameter("number", number).getSingleResult();
       }
       catch (NoResultException ex){
           throw new InvalidRequestException("reservation number does not exist");
       }
    }
}
