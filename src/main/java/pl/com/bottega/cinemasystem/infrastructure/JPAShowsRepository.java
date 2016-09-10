package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
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
}
