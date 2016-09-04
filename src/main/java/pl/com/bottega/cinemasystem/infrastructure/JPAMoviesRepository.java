package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.stereotype.Repository;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.MovieRepository;

import javax.persistence.*;
import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class JPAMoviesRepository implements MovieRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie load(String title, String contest, Collection<String> actors) {
        return null; //TODO
    }
}
