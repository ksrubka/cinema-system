package pl.com.bottega.cinemasystem.infrastructure;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.MovieCatalog;
import pl.com.bottega.cinemasystem.api.MovieDtoWithShows;
import pl.com.bottega.cinemasystem.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by paulina.pislewicz on 2016-09-17.
 */

@Repository
public class JPAMovieCatalog implements MovieCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date) {
        checkNotNull(cinemaId);
        checkNotNull(date);
        String jpa = " FROM Movie m " +
                "JOIN m.cinema c " +
                "JOIN m.show s " +
                "WHERE c.id = :cinemaId AND s.date= :date";

        Query query = (Query) entityManager.createQuery(jpa, MovieDtoWithShows.class);
        query.setParameter("cinemaId", cinemaId);
        query.setParameter("date", date);
        List<Movie> movies = query.getResultList();
        return new ListMoviesInCinemaResponse(movies);
    }

}
