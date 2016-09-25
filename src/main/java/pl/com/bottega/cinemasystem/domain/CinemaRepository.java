package pl.com.bottega.cinemasystem.domain;

public interface CinemaRepository {

    void save(Cinema cinema);

    Cinema load(Long id);

    Cinema load(String city, String name);
}
