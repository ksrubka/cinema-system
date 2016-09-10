package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.ListAllCinemasResponse;
import pl.com.bottega.cinemasystem.domain.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListAllCinemasResponse listAll() {
        String jpa = "select new pl.com.bottega.cinemasystem.api.CinemaDto(" +
        "c.name, + c.city) FROM Cinema c";
        Query query = entityManager.createQuery(jpa);
        return (ListAllCinemasResponse) query.getResultList();
    }
}
