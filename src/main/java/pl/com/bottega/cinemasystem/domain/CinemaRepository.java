package pl.com.bottega.cinemasystem.domain;

public interface CinemaRepository {

    public void save(Cinema cinema);
    public Cinema load(String name, String city);
}
