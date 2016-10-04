package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JPAMoviesRepository implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie load(Long movieId) {
        try {
            return entityManager.createQuery(
                    "FROM Movie m WHERE m.id = :movieId",
                    Movie.class)
                    .setParameter("movieId", movieId)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new InvalidRequestException("No such movie in repository: id " + movieId);
        }
    }

    @Override
    public Movie load(String title) {
        List<Movie> movies = entityManager.createQuery(
                "FROM Movie m WHERE m.title = :title",
                Movie.class)
                .setParameter("title", title)
                .getResultList();
        if (movies.isEmpty()) {
            return null;
        }
        else {
            return movies.get(0);
        }
    }
}