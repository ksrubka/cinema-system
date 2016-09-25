package pl.com.bottega.cinemasystem.infrastructure;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;
import pl.com.bottega.cinemasystem.domain.TicketPrice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;


@Repository
public class JPAShowsRepository implements ShowsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Show show) {
//      checkIfShowAlreadyExists(show);
        entityManager.persist(show);
    }
/*
    private void checkIfShowAlreadyExists(Show show) {
        Long cinemaId = show.getCinema().getId();
        Long movieId = show.getMovie().getId();
        LocalDateTime date = show.getDate().atTime(show.getTime());
        List<Show> shows = entityManager.createQuery(
                "FROM Show s " +
                        "WHERE s.cinema=:cinemaId AND " +
                        "s.movie=:movieId AND s.date=:date", Show.class)
                .setParameter("cinemaId", cinemaId)
                .setParameter("movieId", movieId)
                .setParameter("date", date)
                .getResultList();
        if (!shows.isEmpty()) {
            throw new InvalidRequestException("Can not persist, show already exists: " +
                    "cinemaId: " + cinemaId + ", movieId: " + movieId + ", date: " + date);
        }
    }*/

    @Override
    public Show load(Long id) {
        try {
            return entityManager.createQuery(
                    "FROM Show s WHERE s.id = :id",
                    Show.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No show in repository: id " + id);
        }
    }

    @Override
    public void findShowById(Long showId) {

    }

    @Override
    public void findShowWithReservation(Long showId) {

    }

    /**@Override
    public Set<TicketPrice> loadListOfTicketPrices(Long showId) {
        checkNotNull(showId);
        String jpa = "SELECT DISTINCT S FROM Show S " +
                "JOIN FETCH s.movie m " +
                "JOIN FETCH m.ticketPrices t " +
                "WHERE S.id = :showId";
        TypedQuery<Show> query = entityManager.createQuery(jpa, Show.class);
        query.setParameter("showId", showId);
        Show show = query.getResultList().get(0);
        Set<TicketPrice> price;
        price = show.getMovie().getTicketPrices();
        return price;

    }*/
}