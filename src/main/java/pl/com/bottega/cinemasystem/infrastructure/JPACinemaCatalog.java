package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.ListAllCinemasResponse;

import javax.persistence.*;

@Repository
public class JPACinemaCatalog implements CinemaCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListAllCinemasResponse listAll() {
        return null;
    }
}
