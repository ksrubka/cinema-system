package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.BrowseReservationRequest;
import pl.com.bottega.cinemasystem.api.BrowseReservationResponse;
import pl.com.bottega.cinemasystem.api.ReservationCatalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JPAReservationCatalog implements ReservationCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BrowseReservationResponse browseReservation(BrowseReservationRequest request) {
        return null;
    }
}
