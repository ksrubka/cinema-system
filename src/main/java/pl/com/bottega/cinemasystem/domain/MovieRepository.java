package pl.com.bottega.cinemasystem.domain;

import java.util.List;

public interface MovieRepository {

    void save(Movie movie);

    Movie load(String title, String description, Integer minAge, List<String> actors, List<String> genres, Integer length);

}
