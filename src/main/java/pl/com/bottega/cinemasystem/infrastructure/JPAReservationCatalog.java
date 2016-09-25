package pl.com.bottega.cinemasystem.infrastructure;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.BrowseReservationRequest;
import pl.com.bottega.cinemasystem.api.BrowseReservationResponse;
import pl.com.bottega.cinemasystem.api.ReservationCatalog;
import pl.com.bottega.cinemasystem.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
public class JPAReservationCatalog implements ReservationCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BrowseReservationResponse browseReservation(BrowseReservationRequest request) {
        checkNotNull(request);
        String jpql = buildQuery(request);
        javax.persistence.Query query = entityManager.createQuery(jpql);
        fillStatement(request, query);
        return new BrowseReservationResponse(query.getResultList());
    }

    private void fillStatement(BrowseReservationRequest request, javax.persistence.Query query) {
        if (request.isStatusDefine()) {
            query.setParameter("status", request.getStatus());
        }
        if (request.isLastNameDefine()) {
            query.setParameter("lastName", request.getLastName());
        }
    }

    private String buildQuery(BrowseReservationRequest request) {
        List<String> queryList = new ArrayList<>();
        if (request.isStatusDefine()) {
            queryList.add(" r.status =:status ");

        }
        if (request.isLastNameDefine()) {
            queryList.add(" r.lastName = :lastName");
        }
        String jpql = "SELECT new pl.com.bottega.cinemasystem.api.ReservationDto(" +
                "FROM Reservation d WHERE d.deleted = false " +
                "JOIN FETCH r.status s " +
               "JOIN FETCH r.tickets t " +
               "JOIN FETCH r.bookedSeats b ";

        if (queryList.size() > 0) {
            jpql += "WHERE ";
            int limit = queryList.size();
            for (int index = 0; index < limit; index++) {
                jpql += queryList.get(index);
                if (index < limit - 1)
                    jpql += "AND";
            }
        }
        return jpql;

    }
}
