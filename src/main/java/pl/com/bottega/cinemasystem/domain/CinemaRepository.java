package pl.com.bottega.cinemasystem.domain;

public interface CinemaRepository {

    void save(Cinema cinema);
    Cinema load(String name, String city);
}
