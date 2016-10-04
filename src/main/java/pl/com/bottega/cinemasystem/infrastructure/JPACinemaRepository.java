package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JPACinemaRepository implements CinemaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public Cinema load(Long id) {
        try {
            return entityManager.createQuery(
                    "SELECT DISTINCT c " +
                            "FROM Cinema c WHERE c.id = :id",
                    Cinema.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No such cinema in repository: id= " + id);
        }
    }

    @Override
    public Cinema load(String city, String name) {
        List<Cinema> cinemas = entityManager.createQuery(
                "FROM Cinema c WHERE c.name=:name AND c.city=:city",
                Cinema.class)
                .setParameter("name", name)
                .setParameter("city", city)
                .getResultList();
        if (cinemas.isEmpty()) {
            return null;
        }
        else {
            return cinemas.get(0);
        }
    }
}