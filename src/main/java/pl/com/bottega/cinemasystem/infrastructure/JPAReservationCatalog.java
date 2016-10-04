package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.requests.BrowseReservationRequest;
import pl.com.bottega.cinemasystem.api.responses.BrowseReservationResponse;
import pl.com.bottega.cinemasystem.api.catalogs.ReservationCatalog;
import pl.com.bottega.cinemasystem.api.dtos.ReservationDto;
import pl.com.bottega.cinemasystem.api.utils.DtoMapper;
import pl.com.bottega.cinemasystem.domain.ReservationStatus;

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
    @Transactional
    public BrowseReservationResponse browseReservations(BrowseReservationRequest request) {
        checkNotNull(request);
        String jpql = buildQuery(request);
        Query query = entityManager.createQuery(jpql);
        fillStatement(request, query);
        List<ReservationDto> reservationDtos = DtoMapper.getReservationDto(query.getResultList());
        return new BrowseReservationResponse(reservationDtos);
    }

    private void fillStatement(BrowseReservationRequest request, Query query) {
        if (request.isStatusDefined()) {
            query.setParameter("status", ReservationStatus.valueOf(request.getStatus().toUpperCase()));
        }
        if (request.isLastNameDefined()) {
            query.setParameter("lastName", request.getLastName());
        }
    }

    private String buildQuery(BrowseReservationRequest request) {
        List<String> queryList = new ArrayList<>();
        if (request.isStatusDefined()) {
            queryList.add("r.status =:status");
        }
        if (request.isLastNameDefined()) {
            queryList.add("c.lastName = :lastName");
        }
        String jpql = "SELECT DISTINCT r " +
                "FROM Reservation r " +
                "JOIN FETCH r.tickets t " +
                "JOIN FETCH r.bookedSeats b " +
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
