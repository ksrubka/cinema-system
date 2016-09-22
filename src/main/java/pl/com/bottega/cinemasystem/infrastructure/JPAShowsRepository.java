package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Movie;
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
        entityManager.persist(show);
    }

    @Override
    public Show load(Long id) {
            try {
                return entityManager.createQuery(
                        "FROM Show s WHERE s.id = :id",
                        Show.class)
                        .setParameter("id", id)
                        .getSingleResult();
            }
            catch (Exception ex) {
                throw new InvalidRequestException("No show in repository: id " +id);
            }

    }
}
