package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.ListMoviesInCinemaResponse;
import pl.com.bottega.cinemasystem.api.MovieCatalog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;

@Repository
public class JPAMovieCatalog implements MovieCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ListMoviesInCinemaResponse listMoviesInCinema(Long cinemaId, LocalTime date) {
        /*String jpa = "select new pl.com.bottega.cinemasystem.api.MovieDtoWithShows(" +
                "m.id, m.name, c.city) FROM Movie m";
        Query query = entityManager.createQuery(jpa, CinemaDto.class);
        ListMoviesInCinemaResponse moviesList = new ListMoviesInCinemaResponse(query.getResultList());
        return moviesList;*/
    return null;
    }

}
