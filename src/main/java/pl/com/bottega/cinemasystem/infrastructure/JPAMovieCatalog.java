package pl.com.bottega.cinemasystem.infrastructure;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.responses.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.catalogs.MovieCatalog;
import pl.com.bottega.cinemasystem.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
public class JPAMovieCatalog implements MovieCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalDate date) {
        checkNotNull(cinemaId);
        checkNotNull(date);
        String jpa = "SELECT DISTINCT m FROM Movie m " +
                "JOIN FETCH m.shows s " +
                "JOIN FETCH s.cinema c " +
                "JOIN FETCH m.actors a " +
                "JOIN FETCH m.genres g " +
                "WHERE c.id = :cinemaId AND s.date= :date " +
                "ORDER BY m.title, s.time";

        Query query = (Query) entityManager.createQuery(jpa, Movie.class);
        query.setParameter("cinemaId", cinemaId);
        query.setParameter("date", date);
        List<Movie> movies = query.getResultList();
        return new ListMoviesInCinemaResponse(movies);
    }
}
