package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;
import pl.com.bottega.cinemasystem.domain.TicketPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
public class JPAShowsRepository implements ShowsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Show show) {
        entityManager.persist(show);
    }

    @Override
    public Show load(Long id) {
        try {
            return entityManager.createQuery(
                    "SELECT DISTINCT s " +
                            "FROM Show s WHERE s.id = :id",
                    Show.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No show in repository: id " + id);
        }
    }

    @Override
    public Set<TicketPrice> loadListOfTicketPrices(Long showId) {
        checkNotNull(showId);
        String jpa = "SELECT DISTINCT s FROM Show s " +
                "JOIN FETCH s.movie m " +
                "JOIN FETCH m.ticketPrices t " +
                "WHERE s.id = :showId";
        TypedQuery<Show> query = entityManager.createQuery(jpa, Show.class);
        query.setParameter("showId", showId);
        Show show = query.getResultList().get(0);
        Set<TicketPrice> price;
        price = show.getMovie().getTicketPrices();
        return price;

    }

    @Override
    public void findShowById(Long showId) {

    }

    @Override
    public void findShowWithReservation(Long showId) {

    }
}