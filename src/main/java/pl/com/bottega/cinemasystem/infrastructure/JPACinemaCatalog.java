package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.catalogs.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.dtos.CinemaDto;
import pl.com.bottega.cinemasystem.api.responses.ListAllCinemasResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListAllCinemasResponse listAll() {
        String jpa = "select new pl.com.bottega.cinemasystem.api.CinemaDto(" +
                "c.id, c.name, c.city) FROM Cinema c";
        Query query = entityManager.createQuery(jpa, CinemaDto.class);
        ListAllCinemasResponse cinemasList = new ListAllCinemasResponse(query.getResultList());
        return cinemasList;
    }
}