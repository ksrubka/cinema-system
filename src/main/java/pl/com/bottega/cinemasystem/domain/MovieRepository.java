package pl.com.bottega.cinemasystem.domain;

import java.util.Collection;

public interface MovieRepository {

    void save(Movie movie);

    Movie load(String title, String contest, Collection<String> actors);

}
