package pl.com.bottega.cinemasystem.domain;

public interface MovieRepository {

    void save(Movie movie);
    Movie load(String title);
}
