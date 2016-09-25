package pl.com.bottega.cinemasystem.domain;

public interface ShowsRepository {

    void save(Show show);
    Show load(Long id);
    void findShowById(Long showId);
    void findShowWithReservation(Long showId);
}
