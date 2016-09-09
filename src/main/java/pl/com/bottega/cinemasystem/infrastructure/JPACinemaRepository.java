package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
                    "FROM Cinema c WHERE c.id = :id",
                    Cinema.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No such cinema in repository: id " + id);
        }
    }
}