package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
}