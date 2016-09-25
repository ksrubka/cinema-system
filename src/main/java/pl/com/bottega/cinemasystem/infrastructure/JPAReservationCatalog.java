package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.BrowseReservationRequest;
import pl.com.bottega.cinemasystem.api.BrowseReservationResponse;
import pl.com.bottega.cinemasystem.api.ReservationCatalog;
import pl.com.bottega.cinemasystem.api.utils.DtoMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        Query query = entityManager.createQuery(jpql);
        fillStatement(request, query);
        DtoMapper.getReservationDto(query.getResultList());
        return new BrowseReservationResponse();
    }

    private void fillStatement(BrowseReservationRequest request, Query query) {
        if (request.isStatusDefined()) {
            query.setParameter("status", request.getStatus());
        }
        if (request.isLastNameDefine()) {
            query.setParameter("lastName", request.getLastName());
        }
    }

    private String buildQuery(BrowseReservationRequest request) {
        List<String> queryList = new ArrayList<>();
        if (request.isStatusDefined()) {
            queryList.add("r.status =:status");

        }
        if (request.isLastNameDefine()) {
            queryList.add("r.lastName = :lastName");
        }
        String jpql = "SELECT DISTINCT r " +
                "FROM Reservation r " +
               "JOIN FETCH r.tickets t " +
               "JOIN FETCH r.bookedSeats b "+
                "JOIN FETCH r.customer c " +
                "JOIN FETCH r.show s " +
                "JOIN FETCH s.movie m ";

        if (queryList.size() > 0) {
            jpql += "WHERE ";
            int limit = queryList.size();
            for (int index = 0; index < limit; index++) {
                jpql += queryList.get(index);
                if (index < limit - 1)
                    jpql += " AND ";
            }
        }
        return jpql;

    }
}
